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
	   getFormTable();
}
function populateTable(){
	var leftTable = document.getElementByID("lefttable");
	console.log("here");
}

function modal(){
// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("menubutton");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}