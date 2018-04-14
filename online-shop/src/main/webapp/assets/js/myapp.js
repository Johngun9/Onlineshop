$(function(){
	

	switch (menu) 
	{
	case 'about':
		$('#about').addClass('active');
		
		break;
	case 'contact':
		$('#contact').addClass('active');
		break;
	case 'allproducts':
		$('#allProducts').addClass('active');
		break;
	case 'manageproducts':
		$('#manageProducts').addClass('active');
		break;
	case 'Shopping Cart':
		$('#userModel').addClass('active');
		break;		
	default:
		if (menu == "home")
			$('#home').addClass('active');	
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// code for jquery dataTable
	var $table = $('#productListTable');

	if ($table.length) {
		// console.log('Inside the table!');
		var jsonUrl='';
		if(window.categoryId==''){
			jsonUrl=window.contextRoot+'/json/data/all/products';
		}
		else{
			jsonUrl=window.contextRoot+'/json/data/category/'+window.categoryId+'/products';
			
			console.log("Inside the Else");
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
//									return '&#8377; '+data
									return '<i class="fas fa-rupee-sign"></i>' +data
								}
							},
							{data:'quantity',
								mRender:function(data, type, row){
									
									if(data<1){
										return '<span style="color:red">Out of Stock!!</span>';
									}
									
									return data;
								}	
							},
							
							{data:'id',
								bSortable:false,
								mRender:function(data, type, row){
									var str = '';
									str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a>&#160;';
									
									if(row.quantity<1)
									{
										str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									}
									
									else
									{
										str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									}
									return str;
								}
							}
							
						]
				}
		);
	}
	
	//dismissing the alert after 3 seconds
	/*var $alert=('.alert');
	
	if($alert.length){
		
		setTimeout(function()
				{ 
					$alert.fadeOut("slow");
				}, 3000);
	}*/
	
	//----------------------------------- OnChange--------------
	
		$('.switch input[type="checkbox"]').on('change',function(){
			var checkbox=$(this);
			var checked=checkbox.prop('checked');
			var dMsg=(checked)? 'You want to activate the product?':
								'You want to deactivate the product?';
			var value=checkbox.prop('value');
			//console.log(value);
			bootbox.confirm({
				size : 'medium',
				title : 'Product Activation & Deactivation',
				message: dMsg,
				callback : function(confirmed) {
					if(confirmed){
						console.log(value);
						bootbox.alert({
							size: 'small',
							title: 'Information',
							message: 'You are going to perform operation on product ' + value
						});					
					}
					else {
						checkbox.prop('checked',!checked);					
					}				
				}
			})
		});
	
	
	//	-----------------------------	
	//	Data table for Admin
	//-------------------------------
	
	var $adminProductsTable = $('#adminProductsTable');

	if ($adminProductsTable.length) {
		// console.log('Inside the table!');
		var jsonUrl=window.contextRoot+'/json/data/admin/all/products';
		
		}
		
		$adminProductsTable.DataTable(
				{
				lengthMenu:[[10,30,50,-1],["10 Records","30 Records","50 Records","All Records"]]	,
				pageLength:10,
				ajax:{
					url:jsonUrl,
					dataSrc:''
				},
				columns:[
					
							{
								data:'id'
							},
							{data:'code',
								
								mRender:function(data, type, row){
								return '<img style=" width: 90px " src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="adminProductsTable"/>';
							}
					
							},
									{data:'name'},
									{data:'brand'},							
									{data:'quantity',
										mRender:function(data, type, row){
											
											if(data<1){
												return '<span style="color:red">Out of Stock!!</span>';
											}
											
											return data;
										}	
									},
									
									{data:'untiprice',
										mRender:function(data, type, row){
		//									return '&#8377; '+data
											return '<i class="fas fa-rupee-sign"></i>' +data
										}
									},
									
									{data:'active',
										bSortable:false,
										mRender:function(data, type, row){
											
											var str ='';
											str += '<label class="switch">';
											if(data){
												str +='<input type="checkbox" checked="checked" value="'+row.id+'"/>';
											}
											else 
											{
												str += '<input type="checkbox" value= " '+row.id+' " />';
											}
											
											str += '<div class ="slider round">  </div> </label>';
											
											return str;
										}
									},
									
									{
										data:'id',
										bSortable:false,
										mRender:function(data, type, row){
											
											var str='';
											
											str +='<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
											
											str +='<span class="glyphicon glyphicon-pencil"></span></a>';
											
											return str;
												
										}
									}
							],
							
							initComplete : function(){
								var api = this.api();
								api.$('.switch input[type="checkbox"]').on('change',function(){
									var checkbox=$(this);
									var checked=checkbox.prop('checked');
									var dMsg=(checked)? 'You want to activate the product?':
														'You want to deactivate the product?';
									var value=checkbox.prop('value');
									//console.log(value);
									bootbox.confirm({
										size : 'medium',
										title : 'Product Activation & Deactivation',
										message: dMsg,
										callback : function(confirmed) {
											if(confirmed){
												console.log(value);
												
												var activationUrl = window.contextRoot+'/manage/product/' + value + '/activation';
												
												$.post(activationUrl, function(data){
													bootbox.alert({
														size: 'small',
														title: 'Information',
														message: data
													});		
												});
											}
											else {
												checkbox.prop('checked',!checked);					
											}				
										}
									})
								});
							}
				});
		
	//--------------------------------
	
});
