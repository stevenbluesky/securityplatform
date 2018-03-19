<!-- 新增安装商页面 -->
<#include "/_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
          <form action="/house/supplier/add" method="POST">
          
              <div class="text-center"><h1>新增安装商</h1></div>
              
              <div class="text-left"><h4>安装商信息</h4></div>
              
              <div  class="form-group">
                <label for="organizationid"  class="col-sm-2 control-label">所属安装商</label>
                <div class="col-sm-10">
               		<select id="organizationid" name="organizationid" class="selectpicker" title="选择安装商">
                      <option value="1">安装商1</option>
                      <option value="2">安装商2</option>
                      <option value="3">安装商3</option>
                    </select>
                </div>
              </div>
              
              <div  class="form-group">
                <label for="name"  class="col-sm-2 control-label">公司名称</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="name" name="name" placeholder="Email">
                </div>
              </div>
              
              <div  class="form-group">
                <label for="code"  class="col-sm-2 control-label">公司代码</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="code" name="code" placeholder="Email">
                </div>
              </div>
              
              <div  class="form-group">
                <label for="address"  class="col-sm-2 control-label">公司地址</label>
	                <div class="col-sm-10">
		                 <div class="row text-left">
		                  <div class="col-sm-4">
		                    <select name="country" id="country" class="selectpicker" title="选择国家">
		                    </select>
		                  </div>
		                  <div class="col-sm-4">
		                    <select id="province" name="province" class="selectpicker" title="选择省份">
		                    </select>
		                  </div>
		                  <div class="col-sm-4">
		                    <select id="city" name="city" class="selectpicker" title="选择城市">                          
		                    </select>
		                  </div>
		               </div>
	               </div>
              </div>
              
              <div  class="form-group">
                <label for="detailaddress"  class="col-sm-2 control-label">详细地址</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="detailaddress" name="detailaddress" placeholder="Email">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="postal"  class="col-sm-2 control-label">公司邮编</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="postal" name="postal" placeholder="Email">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="address"  class="col-sm-2 control-label">公司账务地址</label>
                <div class="col-sm-10">
                 <div class="row text-left">
                  <div class="col-sm-4">
                    <select name="country1" id="country1" class="selectpicker" title="选择国家">
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select id="province1" name="province1" class="selectpicker" title="选择省份">
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select id="city1" name="city1" class="selectpicker" title="选择城市">                          
                    </select>
                  </div>
               </div>
               </div>
              </div>
              
              <div  class="form-group">
                <label for="detailaddress"  class="col-sm-2 control-label">账务详细地址</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="detailaddress" placeholder="Email">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="postal"  class="col-sm-2 control-label">公司账务邮编</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="postal" placeholder="Email">
               </div>
              </div>

              <div class="text-left"><h4>安装商联系人</h4></div>
              <div  class="form-group">
                <label for="name"  class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="name" placeholder="Email">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="exampleInputEmail1"  class="col-sm-2 control-label">电话</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Email">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="exampleInputEmail1"  class="col-sm-2 control-label">传真</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Email">
               </div>  
               </div>
                            
              <div  class="form-group">
                <label for="exampleInputEmail1"  class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Email">
               </div>
              </div>

              <div class="text-left"><h4>安装商总公司</h4></div>
              <div  class="form-group">
                <label for="exampleInputEmail1"  class="col-sm-2 control-label">总公司名称</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Email">
               </div>
              </div>
               
              <div  class="form-group">
                <label for="address"  class="col-sm-2 control-label">总公司地址</label>
                <div class="col-sm-10">
                 <div class="row text-left">
                  <div class="col-sm-4">
                    <select name="country2" id="country2" class="selectpicker" title="选择国家">
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select id="province2" name="province2" class="selectpicker" title="选择省份">
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select id="city2" name="city2" class="selectpicker" title="选择城市">                          
                    </select>
                  </div>
               </div>
               </div>
              </div>
              
              <div  class="form-group">
                <label for="postal"  class="col-sm-2 control-label">总公司邮编</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="postal" name="postal" placeholder="postal">
               </div>               
              </div>
              
              <div class="text-left"><h4>安装商总公司联系人</h4></div>
              <div  class="form-group">
                <label for="officemanagername"  class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="officemanagername" placeholder="">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="officemanagerphonenumber"  class="col-sm-2 control-label">电话</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="officemanagerphonenumber" name="officemanagerphonenumber" placeholder="">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="officemanagerfox"  class="col-sm-2 control-label">传真</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="officemanagerfox" name="officemanagerfox" placeholder="">
               </div> 
               </div>
                             
              <div  class="form-group">
                <label for="officemanageremail"  class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="officemanageremail" name="officemanageremail" placeholder="Email">
               </div>
              </div>

              <div class="text-left"><h4>管理员账号</h4></div>
              <div  class="form-group">
                <label for="loginname"  class="col-sm-2 control-label">登录名</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="loginname" name="loginname" placeholder="loginname">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="password"  class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="password" name="password" placeholder="password">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="repassword"  class="col-sm-2 control-label">确认密码</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="repassword" name="repassword" placeholder="repassword">
               </div>
               </div> 
                          
              <div  class="form-group">
                <label for="question"  class="col-sm-2 control-label">密码问题</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="question" name="question" placeholder="question">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label">密码答案</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="answer" name="answer" placeholder="answer">
               </div>
              </div>

              <div class="text-left"><h4>安装商权限</h4></div>
              <div class="row">
	              <div class="col-sm-4"></div>
	              <div class="col-sm-8"><button type="submit" class="btn btn-default" style="width:100px;">提交</button></div>
              </div>
              
        </form>

        </div>

        <div class="col-md-1"></div>
    </div>

<!-- JavaScript 部分 -->
    <script type="text/javascript">
      $("form").submit(function(e){
	});
    </script>
	<script src="../static/js/addressController.js"></script>
	<script src="../static/js/addressController2.js"></script>
<#include "/_foot0.ftl"/>