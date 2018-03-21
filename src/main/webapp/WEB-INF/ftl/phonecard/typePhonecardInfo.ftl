<!-- 录入电话卡信息页面 -->
<#include "/_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
          <form action="/house/supplier/add" method="POST">
          
              <div class="text-center"><h1>录入电话卡信息</h1></div>
             
              <div  class="form-group">
                <label for="name"  class="col-sm-2 control-label">序列号</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="name" name="name" placeholder="序列号">
                </div>
              </div>
              <div  class="form-group">
                <label for="code"  class="col-sm-2 control-label">型号</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="code" name="code" placeholder="型号">
                </div>
              </div>
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label">硬件版本</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="answer" name="answer" placeholder="硬件版本">
               </div>
              </div>
              <div  class="form-group">
                <label for="name"  class="col-sm-2 control-label">Rate Plan</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="name" name="name" placeholder="Rate Plan">
                </div>
              </div>
              <div  cla
              ss="form-group">
                <label for="code"  class="col-sm-2 control-label">Activation Date</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="code" name="code" placeholder="Activation Date">
                </div>
              </div>
              
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label">First programmed On</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="answer" name="answer" placeholder="First programmed On">
               </div>
              </div>
              
              <div  class="form-group">
                <label for="name"  class="col-sm-2 control-label">Last Programmed On</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="name" name="name" placeholder="Last Programmed On">
                </div>
              </div>
              
              <div  class="form-group">
                <label for="code"  class="col-sm-2 control-label">Last Saved On</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="code" name="code" placeholder="Last Saved On">
                </div>
              </div>
              
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label">Dealer</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="answer" name="answer" placeholder="Dealer">
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
<#include "/_foot0.ftl"/>