<!-- 录入电话卡信息页面 -->
<#include "/_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
          <form action="/house/supplier/add" method="POST">
          
              <div class="text-center"><h1>录入电话卡信息</h1></div>
             
              <div  class="form-group">
                <label for="name"  class="col-sm-2 control-label">姓</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="name" name="name" placeholder="姓">
                </div>
              </div>
              <div  class="form-group">
                <label for="code"  class="col-sm-2 control-label">名</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="code" name="code" placeholder="名">
                </div>
              </div>
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label">身份证</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="answer" name="answer" placeholder="身份证">
               </div>
              </div>
             <div  class="form-group">
                <label for="gender"  class="col-sm-2 control-label">性别</label>
                <div class="col-sm-10">
               <select name="gender" class="selectpicker" title="选择性别">
                      <option value="1">男</option>
                      <option value="2">女</option>
                      <option value="3">LGBT</option>
                    </select>
                </div>
              </div>
              <div  class="form-group">
                <label for="code"  class="col-sm-2 control-label">电话</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="code" name="code" placeholder="电话">
                </div>
              </div>
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="answer" name="answer" placeholder="邮箱">
               </div>
              </div>
              <div  class="form-group">
                <label for="name"  class="col-sm-2 control-label">传真</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="name" name="name" placeholder="传真">
                </div>
              </div>
              
                <div  class="form-group">
                <label for="address"  class="col-sm-2 control-label">地址</label>
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
                <label for="code"  class="col-sm-2 control-label">详细地址</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="code" name="code" placeholder="详细地址">
                </div>
              </div>
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label">网关编号</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="answer" name="answer" placeholder="网关编号">
               </div>
               </div>
               
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label">电话卡号</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="answer" name="answer" placeholder="电话卡号">
               </div>
              </div>
              
              <div id="msg" class="text-center">fsdafasd</div>
              <div class="row text-center">
	              <div class="col-sm-6"><button type="submit" class="btn btn-default" style="width:25%;">提交</button></div>
	              <div class="col-sm-6"><button type="reset" class="btn btn-default" style="width:25%;">重置</button></div>
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
<#include "/_foot0.ftl"/>