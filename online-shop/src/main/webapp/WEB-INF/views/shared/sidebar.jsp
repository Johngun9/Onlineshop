
	  <div class="col-lg-3">

		 <h1 class="my-4">Online Shop</h1>
          <div class="list-group">
          
          <c:forEach items="${categories}" var="category">
           	<a href="#" class="list-group-item">${category.name}</a>
           </c:forEach>
           
          </div>
    

        </div>
