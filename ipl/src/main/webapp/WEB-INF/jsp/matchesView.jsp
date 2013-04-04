<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.*" %>
	<%@ page import="com.qaitdevlabs.ipl.dto.*" %>
	<%@ page import="com.qaitdevlabs.ipl.domain.*" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">	
<head>
 <link href="css/tableStyle.css" rel="stylesheet" type="text/css" />
 <!-- <link href="css/login-box.css" rel="stylesheet" type="text/css" /> -->
   <script type="text/javascript" src='js/jquery-1.4.4.min.js'></script>
   <script><!--
      var teamId;
      var selectBoxId;
      var buttonId;
      function getSelectedTeam(selectbox) {
    	  selectBoxId = selectbox.id;
    	  teamId  = selectbox.options[selectbox.selectedIndex].value;
      }
      
      function getSubmitButton(submit) {
    	  buttonId = submit.id;
    	  if(selectBoxId == buttonId) {
    		    var data = { teamId : teamId, matchId : buttonId};
    	      	 $.ajax({
    	       		   url : "userChoice.htm",
    	       		   dataType: "text",
    	       		   data : data,
    	       		   type : "POST",
    	       		   success : function(data) {
    	       			alert("Entry is saved");
    	       			/*  $('input#'+buttonId).after('<span style="color: green;" id="errorSpan" class="error"></br>Entry is saved.</span>');
    	       			 console.log($('input#'+buttonId)); */
    	       		   },
    	       		   error : function(jqXHR, textStatus, errorThrown) {
    	       			alert("Entry was not get saved");
    	       			/*  $("#errorSpan").remove();
    	       			 $('input#'+buttonId).after('<span style="color: red;" id="errorSpan" class="error"></br>Entry was not get saved.</span>');  */
    	       		   }
    	       		  }); 
    	  } else {
    		  alert("Please select proper entry");
    	  }
      }

      $( document ).ready(function() {
    	  $(".test1").each(function () {
           var id = $(this).attr('id');
           //var d1 = new Date("October 13, 1975 11:13:00")
           var dateString = $(this).html();
           dateString = dateString.replace('-',' ');
           dateString = dateString.replace('-',' ');
           var d1 = new Date(dateString);
           var d2 = new Date();
           if((d1.getTime()-1800000)<d2.getTime()){
               $("select."+id).attr('disabled', 'disabled');
               $("input."+id).attr('disabled', 'disabled');
               }

          });
    	});
  
   </script>
</head>
  <body>
     <div>
  	<div class="divStyle">Your Score : 100</div>
 	 <div class="divStyle">Highest Score : 1000</div>
 	 <div class="divStyle">Lowest Score : 0</div>
  	<div class="divStyle">Current Position : 10/12</div>
  	<div class="divStyle" style="float:right"><a href="logout.htm">Log Out</a></div>
  </div>

 <c:if test="${not empty sessionScope.user}"> 
    <div class="divStyle" style="float:right">Welcome ${sessionScope.user.userName}</div>
  </c:if> 
  <br/><br/>
   <div class="CSSTableGenerator" >  
      <table>
              <tr>
                 <td>Match Number</td>
     	         <td>Home Team</td>
     	         <td>Visiting Team</td>
     		 <td>Venue</td>
     	         <td>DateTime</td>
     		 <td>Choice</td> 
     		 <td> </td>
		 <td>Winner</td>
	      </tr>
			 
              <c:forEach var="detail" items="${matchDetails}">
              <tr>
                 <td id="${detail.matchId}">${detail.matchId}</td>
     			 <td>${detail.team1}</td>
     			 <td>${detail.team2}</td>
     			 <td>${detail.venue}</td>
     			 <td id="${detail.matchId}" class="test1">${detail.matchTime}</td>
     			 <td>
     			    <select id="${detail.matchId}" class="${detail.matchId}" onchange="getSelectedTeam(this)">
     			       <option value="0">---Select Team---</option>
     			       <c:choose>
     			          <c:when test="${detail.team1Id eq detail.userChoice}">
     			              <option value="${detail.team1Id}" selected="selected">${detail.team1}</option>
     			          </c:when>
     			          <c:otherwise>
     			              <option value="${detail.team1Id}">${detail.team1}</option>
     			          </c:otherwise>
     			       </c:choose>
     			       
     			       <c:choose>
     			          <c:when test="${detail.team2Id eq detail.userChoice}">
     			              <option value="${detail.team2Id}" selected="selected">${detail.team2}</option>
     			          </c:when>
     			          <c:otherwise>
     			              <option value="${detail.team2Id}">${detail.team2}</option>
     			          </c:otherwise>
     			       </c:choose>
     			  
     			    </select>
     			 </td> 
     			 <td>
     			    <input class="${detail.matchId}" type="submit" value="Save" id="${detail.matchId}" onclick="getSubmitButton(this)"></input>
     			 </td>
     			 <td></td>
     		  </tr>	 
  			  </c:forEach>
      </table>
</div>	
  </body>
</html>