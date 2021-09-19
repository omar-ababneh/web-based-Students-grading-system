<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password] {
  width: 40%;
  padding: 10px;
  margin: 8px;
  display: inline-block;
  border: 1px solid #ccc;
   margin-left: 390px;
}

button {
  background-color: #9999FF;
  color: white;
  padding: 10px;
  margin: 40px;
  border: none;
  cursor: pointer;
  width: 40%;
  margin-left: 400px;
}
.container {
  padding: 10px;
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
</style>
</head>
<body>

<h2>Create Student</h2>
<br>
 <form action="/logout"  method="GET">
    <button type="submit" name="logout" class="design">Logout</button>
    </form>
<form action="createstudent" method="GET">
  <div class="container">
    <input type="text" placeholder="Enter FName" name="fname" required><br>
    <input type="text" placeholder="Enter LName" name="lname" required><br>
        <input type="text" placeholder="Enter GPA" name="gpa" required><br>
        <input type="text" placeholder="Enter Number Of Courses" name="NumCourse" required><br>
      <input type="text" placeholder="Enter Email" name="email" required><br>
        <input type="password" placeholder="Enter Password" name="psw" required><br>
    <button type="submit">Register</button>
  </div>
</form>
</body>
</html>

