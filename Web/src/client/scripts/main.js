"use strict";

const { evenNumbers } = require("./print");
// or import mymodule from 'path/to/module'

evenNumbers.forEach((x, i) => {
	if (i < 5) {
		console.log(i * 3);
	}
});

console.log("Main module loaded.");



function populateStore() {
	$.get("/api/store", function(data) {
		const items = JSON.parse(data).items;
		console.log(items);
		const $target = $("#tab-features");
		items.forEach(item => {
			const timedate = new Date(item.updated_at).toLocaleString();
			console.log(timedate);
			$target.append(`
				<div class="col-sm-4">
					<div class="panel panel-danger">
				        <div class="panel-heading">
				          <h3 class="panel-title">${item.title} - ${item.price}&pound;</h3>
				        </div>
				        <div class="panel-body">
				          <img src="${item.imgUrl}" class="img-thumbnail">
				        	Category: ${item.category} <br>
				        	Added: ${timedate} <br>
				          	<span style="text-shadow: none;">${item.description}</span><br><br>
				          	<button type="button" class="btn btn-danger btn-reserve">Reserve item</button>
				        </div>
			      	</div>
		      </div>`);
		});
		$(".btn-reserve").click(function() {
			console.log(this);
			$(this).text("Reserved");
			$(this).attr('disabled','disabled');
		});
	});
}


populateStore();
