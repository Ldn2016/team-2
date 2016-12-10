"use strict";

const mongoose = require('mongoose');
const Schema = mongoose.Schema;
const crypto = require('crypto');
const config = require('../config');

function hash(text) {
	let h = crypto.createHmac('sha256', config.HASH_SECRET).update(text);
	return h.digest('hex');
}

// create a schema
let storeItemSchema = new Schema({
	title: { type: String, required: true },
	description: String,
	suggestedPrice: Number,
	imgUrl: String,
	price: Number,
	locLat: Number,
	locLng: Number,
	status: String,
	category: String,
	userId: String,
	created_at: { type: Date, default: Date.now },
	updated_at: Date
});

// Pre-hook/middleware function for the save() function
storeItemSchema.pre('save', function(next) {
	
	let currentDate = new Date();
	// change the updated_at field to current date
	this.updated_at = currentDate;
	if (!this.created_at) { // if doesn't exist
		this.created_at = currentDate;
	}
	next();
});

// the schema is useless so far -> create a model using it
const StoreItem = mongoose.model('StoreItem', storeItemSchema);

module.exports = StoreItem; // make the model available 

