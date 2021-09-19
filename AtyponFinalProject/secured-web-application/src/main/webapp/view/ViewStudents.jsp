<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style>
table, th, td {
  border: 1px solid black;
}
th,td{
text-align:center;
font-size:20px;
color:blue;
}
button {
  background-color: #9999FF;
  color: white;
  text-align:center
  padding: 10px;
  border: none;
  cursor: pointer;
  width: 40%;
}
.design{
   top: 0px;
   right: 0px;
   position: absolute;
   background-color: #9999FF;
   color: white;
   padding: 10px;
   border: none;
   cursor: pointer;
   width: 20%;
}
table{
width:100%;
}
</style>
</head>

<body>
<br>
  <form action="/logout"  method="GET">
    <button type="submit" name="logout" class="design">Logout</button>
    </form>
<table>
  <tr>
     <th>First Name</th>
      <th>Last Name</th>
      <th> Number Of Course</th>
      <th>GPA</th>
  </tr>

<c:forEach items="${Students}" var="entry">
    <tr>
     <td>${entry.getFName()}</td>
     <td> ${entry.getLName()}</td>
     <td> ${entry.getNumOfCourse()}</td>
     <td> ${entry.getGPA()}</td>
    </tr>
</c:forEach>
 </table>
</body>
</html>
