function validateForm()
{

var cus_id=document.getElementById("cus_id").value;
var cus_name=document.forms["myForm"]["cus_name"].value;
var address=document.forms["myForm"]["address"].value;
var buy_date=document.forms["myForm"]["buy_date"].value;
var buy_price=document.forms["myForm"]["buy_price"].value;
var saled_price=document.forms["myForm"]["saled_price"].value;
var advance=document.forms["myForm"]["advance"].value;
var tot_dues=document.forms["myForm"]["tot_dues"].value;
var due_time=document.forms["myForm"]["due_time"].value;

var errors = []; 

if (isEmpty(cus_id))
  {
	
	 errors[errors.length]="Custmer Id must be filled out";
  }
  if (isEmpty(cus_name))
  {
  errors[errors.length]="Custmere name field must be filled out";
   }
 if (isEmpty(address))
  {
  errors[errors.length]="Address field must be filled out";
   }
  if (isEmpty(buy_date))
  {
  errors[errors.length]="Buying date field must be filled out";
   }
    if (isEmpty(buy_price))
  {
  errors[errors.length]="Buying Price field must be filled out";
   }
    if (isEmpty(saled_price))
  {
  errors[errors.length]="Selling Price field must be filled out";
   }
 
  if (isEmpty(advance))
  {
  errors[errors.length]="advance field must be filled out";
   }
  if (isEmpty(tot_dues))
  {
  errors[errors.length]="Total dues field must be filled out";
   }
  if (isEmpty(due_time))
  {
  errors[errors.length]="Due time field must be filled out";
   }

 if (errors.length > 0) 
           {
           reportErrors(errors);
           return false;
           }
           


}


function validateCheetyForm()
{

var mem_id=document.forms["myForm"]["mem_id"].value;
var mem_name=document.forms["myForm"]["mem_name"].value;
var address=document.forms["myForm"]["address"].value;

var errors = []; 

if (isEmpty(mem_id))
  {
	
	 errors[errors.length]="Member Id must be filled out";
  }
  if (isEmpty(mem_name))
  {
  errors[errors.length]="Member name field must be filled out";
   }
 if (isEmpty(address))
  {
  errors[errors.length]="Address field must be filled out";
   }
 
   

 if (errors.length > 0) 
           {
           reportErrors(errors);
           return false;
           }
           


}
function validateCheetyUpdcateForm()
{

var cheety=document.forms["myForm"]["cheety"].value;
var join_date=document.forms["myForm"]["join_date"].value;
var cheety_members=document.forms["myForm"]["cheety_members"].value;
var cheety_months=document.forms["myForm"]["cheety_months"].value;
var cheety_members=document.forms["myForm"]["cheety_members"].value;
var cheety_min_amt=document.forms["myForm"]["cheety_min_amt"].value;
var errors = []; 

if (isEmpty(cheety))
  {
	
	 errors[errors.length]="Cheety must be filled out";
  }
  if (isEmpty(join_date))
  {
  errors[errors.length]="Cheety Date field must be filled out";
   }
 if (isEmpty(cheety_members))
  {
  errors[errors.length]="Cheety Members field must be filled out";
   }
  if (isEmpty(cheety_months))
  {
  errors[errors.length]="Cheety Months field must be filled out";
   }
 
  if (isEmpty(cheety_min_amt))
  {
  errors[errors.length]="Cheety Mim Amt field must be filled out";
   }  

 if (errors.length > 0) 
           {
           reportErrors(errors);
           return false;
           }
           


}
  

function validateCheetyUpdcateMonthllyForm()
{

var mem_id=document.forms["myForm"]["mem_id"].value;
var cheety_no=document.forms["myForm"]["cheety_no"].value;
var cheety_amount=document.forms["myForm"]["cheety_amount"].value;
var cheety_date=document.forms["myForm"]["cheety_date"].value;
var cheety_status=document.forms["myForm"]["cheety_status"].value;

var errors = []; 

if (isEmpty(mem_id))
  {
	
	 errors[errors.length]="Member Id must be filled out";
  }
  if (isEmpty(cheety_no))
  {
  errors[errors.length]="Cheety No field must be filled out";
   }
 if (isEmpty(cheety_amount))
  {
  errors[errors.length]="Cheety Amount field must be filled out";
   }
  if (isEmpty(cheety_date))
  {
  errors[errors.length]="Cheety Date field must be filled out";
   }
 
  if (isEmpty(cheety_status))
  {
  errors[errors.length]="Cheety Status Amt field must be filled out";
   }  

 if (errors.length > 0) 
           {
           reportErrors(errors);
           return false;
           }
           


}

 function isEmpty(inputStr) 
           { 
	        if ( null == inputStr || "" == inputStr ) 
	        {
	        return true;
 	         }
          
        }

		function reportErrors(errors)
           {
           	
	         msg = "Please Enter Valid Data...\n";
	         for (var i = 0; i < errors.length; i++) 
	         {
	          var numError = i + 1;
	          msg += "\n" + numError + ". " + errors[i];
	          }
 	          alert(msg); 
        }

function validateId()
{
var cus_id=document.getElementById("cus_id").value;

if (isEmpty(cus_id))
  {
	
	 alert("Please Enter Custmer Id ");
	 return false;
  }



}

function validateName()
{
var cus_name=document.getElementById("cus_name").value;

if (isEmpty(cus_name))
  {
	
	 alert("Please Enter Custmer Name ");
	 return false;
  }



}


function validateItemName()
{
	 alert("Please Enter Item Name ");
var item_name=document.getElementById("item_name").value;

if (isEmpty(item_name))
  {
	
	 alert("Please Enter Item Name ");
	 return false;
  }



}


function validateModelName()
{
var model_name=document.getElementById("model_name").value;

if (isEmpty(model_name))
  {
	
	 alert("Please Enter Model Name ");
	 return false;
  }



}
function validateShopName()
{
var shop_name=document.getElementById("shop_name").value;

if (isEmpty(shop_name))
  {
	
	 alert("Please Enter Shop Name ");
	 return false;
  }



}