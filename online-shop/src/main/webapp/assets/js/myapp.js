$(function(){
	

	switch (menu) 
	{
	case 'about':
		$('#about').addClass('active');
		break;
	case 'contact':
		$('#contact').addClass('active');
		break;
	case 'all products':
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
	
	if ($table.length) {
		// console.log('Inside the table!');
		var jsonUrl='';
		if(window.categoryId==''){
			jsonUrl=window.contextRoot+'/json/data/all/products';
		}
		else{
			jsonUrl=window.contextRoot+'json/data/category/'+window.categoryId+'products';
		}
		
		$table.DataTable(
				{
				lengthMenu:[[3,5,8,-1],["3 Records","5 Records","8 Records","All Records"]]	,
				pageLength:5,
				ajax:{
					url:jsonUrl,
					dataSrc:''
				},
				columns:[	{data:'code',
						
						mRender:function(data, type, row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
					}
			
					},
							{data:'name'},
							{data:'brand'},
							{data:'untiprice',
								mRender:function(data, type, row){
									return '&#8377; '+data
								}
							},
							{data:'quantity'},
							
							{data:'id',
								bSortable:false,
								mRender:function(data, type, row){
									var str = '';
									str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>&#160;';
									str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									
									return str;
								}
							}
							
						]
				}
		);
	}
});
