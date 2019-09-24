<%--
  Created by IntelliJ IDEA.
  User: ruanxk
  Date: 2019-09-23
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="./js/profile.js"></script>
</head>
<body>
<div class="container">
    <h1>Edit Profile</h1>
    <hr>

    <div class="row">
        <!-- left column -->
        <div class="col-md-3">
            <div class="text-center">
                <img src="//placehold.it/100" class="avatar img-circle" alt="avatar">
                <h6>Upload a different photo...</h6>

                <input type="file" class="form-control">
            </div>
        </div>

        <!-- edit form column -->
        <div class="col-md-9 personal-info">
            <div class="alert alert-info alert-dismissable">
                <a class="panel-close close" data-dismiss="alert">×</a>
                <i class="fa fa-coffee"></i>
                This is an <strong>.alert</strong>. Use this to show important messages to the user.
            </div>
            <h3>Personal info</h3>

            <form class="form-horizontal" role="form">
                <!--
               <div class="form-group">
                   <label class="col-lg-3 control-label">First name:</label>
                   <div class="col-lg-8">
                       <input class="form-control" type="text" value="Jane">
                   </div>
               </div>
               <div class="form-group">
                   <label class="col-lg-3 control-label">Last name:</label>
                   <div class="col-lg-8">
                       <input class="form-control" type="text" value="Bishop">
                   </div>
               </div>

               <div class="form-group">
                   <label class="col-lg-3 control-label">Company:</label>
                   <div class="col-lg-8">
                       <input class="form-control" type="text" value="">
                   </div>
               </div>
               <div class="form-group">
                   <label class="col-lg-3 control-label">Email:</label>
                   <div class="col-lg-8">
                       <input class="form-control" type="text" value="janesemail@gmail.com">
                   </div>
               </div>
               <div class="form-group">
                   <label class="col-lg-3 control-label">Time Zone:</label>
                   <div class="col-lg-8">
                       <div class="ui-select">
                           <select id="user_time_zone" class="form-control">
                               <option value="Hawaii">(GMT-10:00) Hawaii</option>
                               <option value="Alaska">(GMT-09:00) Alaska</option>
                               <option value="Pacific Time (US &amp; Canada)">(GMT-08:00) Pacific Time (US &amp; Canada)</option>
                               <option value="Arizona">(GMT-07:00) Arizona</option>
                               <option value="Mountain Time (US &amp; Canada)">(GMT-07:00) Mountain Time (US &amp; Canada)</option>
                               <option value="Central Time (US &amp; Canada)" selected="selected">(GMT-06:00) Central Time (US &amp; Canada)</option>
                               <option value="Eastern Time (US &amp; Canada)">(GMT-05:00) Eastern Time (US &amp; Canada)</option>
                               <option value="Indiana (East)">(GMT-05:00) Indiana (East)</option>
                           </select>
                       </div>
                   </div>
               </div>
               -->
               <div class="form-group">
                   <label class="col-md-3 control-label">Username:</label>
                   <div class="col-md-8">
                       <input class="form-control" type="text" id="name" value="">
                   </div>
               </div>
               <div class="form-group">
                   <label class="col-md-3 control-label">Password:</label>
                   <div class="col-md-8">
                       <input class="form-control" type="password" id="password" value="">
                   </div>
               </div>
               <div class="form-group">
                   <label class="col-md-3 control-label">Confirm password:</label>
                   <div class="col-md-8">
                       <input class="form-control" type="password" id="re_pass" value="">
                   </div>
               </div>
               <div class="form-group">
                   <label class="col-md-3 control-label"></label>
                   <div class="col-md-8">
                       <input type="button" class="btn btn-primary" value="Save Changes">
                       <span></span>
                       <input type="reset" class="btn btn-default" value="Cancel">
                   </div>
               </div>
           </form>
       </div>
   </div>
</div>
<hr>
</body>
</html>
