// JavaScript Document
function changemenu(divbgcolor,temp)
{
var div="div"+temp; var dv="dv"+temp; var a="a"+temp; 

document.getElementById(div).style.backgroundColor=divbgcolor;
document.getElementById(a).style.color="#ffffff";
document.getElementById(a).style.fontWeight="bold";
document.getElementById(dv).style.backgroundColor="#ffffff";
 backout(temp);
}
function backmenu(dvbgcolor,temp){
var div="div"+temp; var dv="dv"+temp; var a="a"+temp; 
document.getElementById(div).style.backgroundColor="";
document.getElementById(a).style.color="#000000";
document.getElementById(a).style.fontWeight="normal";
document.getElementById(dv).style.backgroundColor=dvbgcolor;
}
function backout(temp){
if(temp!="user"){backmenu("#990000","user");}
if(temp!="product"){backmenu("#ffc300","product");}
if(temp!="order"){backmenu("#007300","order");}
if(temp!="district"){backmenu("#0e0077","district");}
if(temp!="comment"){backmenu("#ff4700","comment");}
if(temp!="admin"){backmenu("#d80000","admin");}
}
function clicka(id){
if(id=="auser"){changemenu("#990000","user");}
if(id=="aproduct"){changemenu("#ffc300","product");}
if(id=="aorder"){changemenu("#007300","order");}
if(id=="adistrict"){changemenu("#0e0077","district");}
if(id=="acomment"){changemenu("#ff4700","comment");}
if(id=="aadmin"){changemenu("#d80000","admin");}
}