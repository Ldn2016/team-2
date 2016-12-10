"use strict";

const { evenNumbers } = require("./print");
// or import mymodule from 'path/to/module'

evenNumbers.forEach((x, i) => {
	if (i < 5) {
		console.log(i * 3);
	}
});

console.log("Main module loaded.");



function populateStore(category) {
	$.get("/api/store", function(data) {
		const items = JSON.parse(data).items;
		console.log(items);
		const $target = $("#tab-features-content");
		$target.html("");
		const $target2 = $("tbody");
		$target2.html("");
		let numun = 0;
		items.forEach(item => {
			const timedate = new Date(item.updated_at).toLocaleString();
			console.log(timedate);
			
			if (item.status == "queue") {

				numun++;
				$target2.append(`
					<tr>
	                <th>${numun}</th>
	                <th><img height="100px" src="${item.imgUrl}"></th>
	                <th>${item.title}</th>
	                <th>${item._id}</th>
	                <th><input type="text" style="text-align:right" value="${item.price}">&pound;</th>
	                <th><input type="text" placeholder="Category" value="${item.category}"></th>
	                <th>${item.userId}</th>
	                <th>
	                	<button type="button" class="btn btn-danger btn-approve">
	                		<i class="fa fa-check" aria-hidden="true"></i>
	                	</button>
	                	<button type="button" class="btn btn-danger btn-reject">
	                		<i class="fa fa-times" aria-hidden="true"></i>
	                	</button>
	                </th>
	              </tr>
	             `);
			} else {
				console.log(category, item.category);
				if (category && category != item.category) return;
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
			}
		});
		$(".btn-approve").click(function() {

			const a = {
				id: $($(this).parent().parent().children("th")[3]).text(),
				title: $($(this).parent().parent().children("th")[2]).text(),
				price: parseFloat($($(this).parent().parent().children("th")[4]).children("input")[0].value),
				category: $($(this).parent().parent().children("th")[5]).children("input")[0].value
			};
			console.log(a);
			notifyClicked(a);
			$(this).parent().parent().fadeOut()
			
		});
		$(".btn-reject").click(function(){
			$(this).parent().parent().fadeOut()
		});
		$(".btn-reserve").click(function() {
			console.log(this);
			$(this).text("Reserved");
			$(this).attr('disabled','disabled');
		});
	});
}


setTimeout(populateStore, 1200);
window.populateStore = populateStore;