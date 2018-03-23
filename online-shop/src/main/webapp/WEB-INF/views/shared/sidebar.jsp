
	  <div >

		 <h1 class="my-4">Online Shop</h1>
		 
		  
          <ul class="list-group ">
          <c:forEach items="${categories}" var="category">
         
           	<li class="list-group-item"> <a href="${contextRoot}/show/category/${category.id}/products"  id="a_${category.name}">${category.name}</a> </li>
           </c:forEach>
           
          </ul>
    

        </div>
