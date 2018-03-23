$(function(){
	

	switch (menu) 
	{
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Product Management':
		$('#manageProduct').addClass('active');
		break;
	case 'Shopping Cart':
		$('#userModel').addClass('active');
		break;		
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// code for jquery dataTable
	var $table = $('#productListTable');
	
//	var products=[
//					["1","ABC"],
//					["2","DEF"],
//					["3","GHI"],
//					["4","JKL"],
//					["5","MNO"],
//					["6","PQR"],
//					["7","STU"]
//	];
	
	// execute the below code only where we have this table
	var jsonUrl='';
	if(window.categoryId==''){
		jsonUrl=window.contextRoot+'/json/data/all/products';
	}
	else{
		jsonUrl=window.contextRoot+'json/data/category/'+window.categoryId+'products';
	}
	if ($table.length) {
		// console.log('Inside the table!');
		$table.DataTable(
				{
				lengthMenu:[[3,5,8,-1],["3 Records","5 Records","8 Records","All Records"]]	,
				pageLength:5,
				ajax:{
					url:jsonUrl,
					dataSrc:''
				},
				columns:[
							{data:'name'},
							{data:'brand'},
							{data:'unitPrice',
								mRender:function(data,type,row){
									return '&#8377; '+data
								}
							},
							{data:'quantity'},
							{data:'id',
								bSortable:false,
								rRender:function(data,type,row){
									var str='';
									str+='<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>&#160;';
									str+='<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									
									return str;
								}
							}
							
						]
				}
		);
	}
});
