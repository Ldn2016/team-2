"use strict";

const express = require('express');
const bodyParser = require('body-parser'); // additional body parsing
const multer = require('multer'); // file upload (multipart/form-data)
const morgan = require('morgan'); // General request logger
const Cookies = require('cookies'); // General cookie handling
const session = require('express-session'); // SESSION cookies
const MongoStore = require('connect-mongo')(session); // Session data storage (server-side MongoDB)
const mongoose = require('mongoose'); // ORM for MongoDB
const path = require('path');
const pp = s => path.join(__dirname, s);
const app = express();
const server = require('http').createServer(app); // or https
const config = require('./config');

app.set('views', pp('../client/views')); // where templates are located
app.set('view engine', 'pug'); // Express loads the module internally

app.use(Cookies.express());

// Add top-level (could be made route-specific) parsers that will populate request.body
app.use(bodyParser.urlencoded({ extended: false })); // application/x-www-form-urlencoded
app.use(bodyParser.json()); // application/json
const upload = multer({ dest: pp('../client/public/uploads/') }); // Use with multipart/form-data

app.use(morgan('dev')); // Set up logger
const debug = require('./utils/debug'); // + my own logger
app.use(debug.requestInfo); 

mongoose.connect(config.MONGODB_URI); // Connect to MongoDB

// Set up secure SESSION cookies
app.use(session({
	secret: config.APP_SECRET,
	saveUninitialized: false,
	resave: false, // keep the most recent session modification
	store: new MongoStore({ mongooseConnection: mongoose.connection })
}));

/** Route handlers */
const { io: sockets } = require('./controllers/shopDashboard');
sockets.attach(server); // attach() is Socket.IO specific

const StoreItem = require('./models/StoreItem');
const store = require('./controllers/store');
store.init();

app.get('/routes', function(req, res) {
	res.send(`
		<h1>API</h1>
		GET <a href="/api/store">/api/store</a> - get store listing<br>
		POST /api/queue - add new product to the shop assistant's approval queue<br>
		<hr><br>
		<h1>Web</h1>
		GET <a href="/">/</a> - Main website<br>
		GET <a href="/management/shop">/management/shop</a> - Shop assistant control dashboard<br>
	`);
});

app.get('/', (req, res) => {
	res.render('index');
});

app.get('/management/shop', (req, res) => {
	res.render('shop');
});

app.get('/api/store', (req, res) => {
	store.getAllItems().then(items => {
		res.jsonPretty({ timedate: Date.now(), items: items });
	});
});

app.get('/api/uploads', (req, res) => {
	store.getAllItems().then(items => {
		res.jsonPretty({ timedate: Date.now(), items: items.filter(item => item.userId != undefined) });
	});
});

app.post('/api/queue', upload.single('avatar'), function(req, res) {
	//console.log(req.file); // uploaded file info
	//if(req.file) console.log(req.file.path + " " + req.file.filename); // where it's stored
	console.log(req.body); // text form-fields

	const base64Data = req.body.file.replace(/^data:image\/png;base64,/, "");
	const imgUrl = "/uploads/" + Math.round(Math.random() * 100000) + ".jpg";
	require("fs").writeFile(path.join("../client/public", imgUrl) , base64Data, 'base64', function(err) {
	  console.log(err);
	});

	store.addItem(new StoreItem({
		title: req.body.title,
		description: req.body.description,
		suggestedPrice: req.body.suggestedPrice,
		price: req.body.suggestedPrice,
		imgUrl: imgUrl,
		userId: "5118827",
		status: "queue"
	}));

	/* Broadcast to everyone */
	sockets.emit('new_item');
	res.send("ok");
});

app.get('/clean', (req, res) => {
	store.clean();
	res.send("ok");
});

app.get('/debug-new-item', (req, res) => {
	sockets.emit('new_item');
	res.send("ok");
});

module.exports = {
	server: server,
	app: app
};
