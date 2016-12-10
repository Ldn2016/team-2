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
function approveItem() {
	StoreItem.findById(1, (err, item) => {
		if (err) throw err;

		item.status = 'approved';

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
				descr: 'Size 10 - Blue - Sleeveless',
				price: 17.99
			}));
			addItem(new StoreItem({
				title: 'The Father Christmas Letters',
				descr: 'J.R.R. Tolkien - The Father Christmas Letters - Good condition',
				price: 2.99
			}));
			addItem(new StoreItem({
				title: 'Crystal Rose Bowl',
				descr: 'Vintage pressed green glass',
				price: 7.99
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
	getAllItems
};
