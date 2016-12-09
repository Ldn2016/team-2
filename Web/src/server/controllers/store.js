"use strict";

// Iterface functions for our Mongoose MongoDB model
const StoreItem = require('../models/StoreItem');

function addItem(item) {
	// call the built-in save() to save to the database
	item.save((err) => {
		if (err) throw err;
		console.log('Item added successfully!');
	});
};

// FIND - find(), findOne(), findById()
function getAllItems(callback) {
	StoreItem.find({}, (err, items) => {
		if (err) throw err;
		
		callback(items); // all the items
	});
};

function getUserItems(userId) {
	// get the user 'alice'
	StoreItem.find({ userId: userId }, (err, item) => {
		if (err) throw err;

		console.log(item); // item object
	});
};

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

function listAll(req, res) { // route
	getAllItems((items) => res.jsonPretty({ items: items }));
}

module.export = {
	addItem,
	getAllItems,
	listAll
};
