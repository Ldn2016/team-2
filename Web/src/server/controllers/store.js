"use strict";

// Iterface functions for our Mongoose MongoDB model
const StoreItem = require('../models/StoreItem');

function addItem(item) {
	// call the built-in save() to save to the database
	item.save((err) => {
		if (err) throw err;
		console.log('Item added successfully!');
	});
}

// FIND - find(), findOne(), findById()
function getAllItems() {
	return new Promise((resolve, reject) => {
		StoreItem.find({}, (err, items) => {
			if (err) reject(err);
			
			resolve(items); // all the items
		});
	});
}

function getUserItems(userId) {
	// get the user 'alice'
	StoreItem.find({ userId: userId }, (err, item) => {
		if (err) throw err;

		console.log(item); // item object
	});
} 

// UPDATE = FIND + SAVE
function approveItem(data) {
	StoreItem.findById(data.id, (err, item) => {
		if (err) throw err;

		item.status = 'approved';
		item.price = parseInt(data.price);
		item.category = data.category;

		item.save((err) => {
			if (err) throw err;

			console.log('Item successfully updated!');
		});
	});
}

function init() {
	StoreItem.find({}, (err, items) => {
		console.log(items.length);
		if (!items.length) {
			addItem(new StoreItem({
				title: 'Brand New Dress Reiss',
				description: 'Size 10 - Blue - Sleeveless',
				imgUrl: 'http://i.ebayimg.com/00/s/NTAwWDQzNg==/z/EuYAAOxy63FSsTa2/$_35.JPG?set_id=2',
				price: 17.99,
				category: "Clothing"
			}));
			addItem(new StoreItem({
				title: 'The Father Christmas Letters',
				description: 'J.R.R. Tolkien - The Father Christmas Letters - Good condition',
				imgUrl: 'https://upload.wikimedia.org/wikipedia/en/thumb/9/93/FatherChristmasLetters.JPG/220px-FatherChristmasLetters.JPG',
				price: 2.99,
				category: "Books"
			}));
			addItem(new StoreItem({
				title: 'Crystal Rose Bowl',
				description: 'Vintage pressed green glass',
				imgUrl: 'http://thumbs.picclick.com/00/s/MTIwMFgxNjAw/z/Oa8AAOSwKtVW00LR/$/Vintage-Rose-Bowl-Royal-Brierley-Crystal-Cut-Glass-_57.jpg',
				price: 7.99,
				category: "Accessories"
			}));
		}
	});
}

function clean() {
	StoreItem.remove({}, (err) => {
		if (err) throw err; 
		console.log('Items removed');
		StoreItem.find({}, (err, items) => {
			console.log(items.length);
		});
	});
}

module.exports = {
	init,
	clean,
	addItem,
	getAllItems,
	approveItem
};
