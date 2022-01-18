<%
boolean isUserAdmin= false ;
String contextPath = request.getContextPath();
String userType =(String) session.getAttribute("USER_TYPE");
userType = (userType !=null)? userType : "";
if(userType.length() > 0 && userType.equals("ADMIN")){
	isUserAdmin = true ;
}
%>
<head>
</head>
<div id='cssmenu'>
<ul>
   <li class='active'></li>
   <li class='has-sub'><a href='#'><span>FILE CREATION</span></a>
      <ul>
         <li><a href='userRegister'><span>File Creation</span></a></li> 
         <% if(isUserAdmin){%>
         <li><a href='adminRegistration'><span>Admin Registration</span></a></li>
         <%} %>
         <!-- <li class='last'><a href='#'><span>Product 3</span></a></li> -->
      </ul>
   </li>
   <li class='has-sub'><a href='#'><span>DAILY LEDGER</span></a>
      <ul>
         <li><a href='dailyLedger'><span>Create Ledger</span></a></li>
         <li><a href='viewLedger'><span>Show Ledger</span></a></li>
         <li><a href='downloadLedgerPage'><span>Download Ledger</span></a></li>
      </ul>
   </li>
   <% if(isUserAdmin){%>
   <li class='has-sub'><a href='#'><span>ROLE CREATION</span></a>
      <ul>
         <li><a href='adminRegistration'><span>Role Create</span></a></li>
         <li><a href='userAdminDeletePage'><span>Role Deletion</span></a></li>
         <li><a href='viewAdminUsersPage'><span>View User</span></a></li>
      </ul>
   </li>
    <%} %>
   <li class='has-sub'><a href='#'><span>UPDATION</span></a>
      <ul>
         <li><a href='userUpdation'><span>User Updation</span></a></li>
      </ul>
   </li>
   <li class='has-sub'><a href='#'><span>VIEW</span></a>
      <ul>
         <li><a href='viewUserDetailsPage'><span>View User Details</span></a></li>
      </ul>
   </li>
    <% if(isUserAdmin){%>
          <li class='has-sub'><a href='#'><span>DELETE</span></a>
		      <ul>
		        <!--  <li><a href='userDelete'><span>Delete User</span></a></li> -->
		        <li><a href='userUpdateDelete'><span>Delete User</span></a></li>
		      </ul>
   			</li>
    <%} %>
    <li class='has-sub'><a href='#'><span>UPDATE DATE</span></a>
      <ul>
         <li><a href='updateDate'><span>Update Date</span></a></li>
      </ul> 
   </li>
   <li class='has-sub'><a href='#'><span>DOWNLOADS</span></a>
      <ul>
         <li><a href='userDownload'><span>Payment Details</span></a></li>
	 	<li><a href='productDownload'><span>Product Details</span></a></li>
	 	<li><a href='historyDownload'><span>Due History</span></a></li>
      </ul> 
   </li>
   <li class='has-sub'><a href='#'><span>PRODUCTS</span></a>
      <ul>
         <li><a href='createProduct'><span>Create Product</span></a></li>
	 	<li><a href='createProduct'><span>Create Brand</span></a></li>
      </ul> 
   </li>
   <li class='has-sub'><a href='#'><span>CHEETY</span></a>
      <ul>
         <li><a href='showCheety'><span>Create</span></a></li>
         <li><a href='viewCheety'><span>View</span></a></li>
      </ul>
   </li>
   <li class='last'><a href='#'><span>Contact</span></a></li>
</ul>
</div>


