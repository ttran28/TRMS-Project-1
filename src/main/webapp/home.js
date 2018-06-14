function loadFormTable(table){
   document.getElementById("formTable").innerHTML=table;
}

function getFormTable(){
	   console.log("in getFormTable");
	   var tableID = document.getElementById("formTable");
	   tableID.innerHTML = "";
	   var headrow = tableID.insertRow(0);
	   var headcell = headrow.insertCell(0);
	   console.log(headrow.length);
	   //Step 1
	   var xhr =  new XMLHttpRequest();
	   //Step 2 function to handle onreadystatechange of response
	   xhr.onreadystatechange=function (){
	       console.log("roll tide");
	       if(xhr.readyState ==4 && xhr.status ==200){
	           console.log(xhr.responseText);
	           var table= xhr.responseText;
	           loadFormTable(table);
	       }else{
	    	   console.log("State not ready! ");
	       }
	   }
	   //Step 3 open the request/connection
	   xhr.open("GET", "JSON.txt",true);
	   //Step 4 send the request
	   xhr.send();
	}
// When the user clicks anywhere outside of the modal, close it
window.onload =  function (){
	   console.log("in onLoad");
	   document.getElementById("formTable").addEventListener("click", 
			   getFormTable, false);
}