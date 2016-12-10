"use strict";

const io = require('socket.io')(); // new Server
const store = require('./store');
let users = [];

io.on('connection', (socket) => {
	let uid = socket.id;
	users.push(uid);
	console.log("socket.io - a user connected - " + uid);

	socket.on('item_approved', (data) => {
		console.log(data);
		store.approveItem(data);
	});

	socket.on('disconnect', () => {
		console.log('user disconnected');
		let index = users.indexOf(uid);
		users.splice(index, 1); // remove from the array
	});
});

module.exports = { io };
