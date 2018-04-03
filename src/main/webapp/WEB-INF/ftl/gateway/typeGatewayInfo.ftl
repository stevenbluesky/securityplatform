<#ftl strip_whitespace=true>
<#import "/spring.ftl" as spring />

<!-- 录入网关信息页面 -->
<#include "../_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
          <form id="typeform" action="../gateway/add" method="get">
              <div class="text-center"><h1><@spring.message code="label.enteringgatewayinfo"/></h1></div>            
              <div  class="form-group">
                <label for="name"  class="col-sm-2 control-label"><@spring.message code="label.gatewayID"/>*</label>
                <div class="col-sm-10">
                <input type="text"  class="form-control" id="deviceid" name="deviceid" placeholder="deviceid" <#if deviceid??>value=${deviceid}</#if>>
                </div>
              </div>
              <div  class="form-group">
                <label for="code"  class="col-sm-2 control-label"><@spring.message code="label.model"/>*</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="model" name="model" placeholder="model" <#if model??>value=${model}</#if>>
                </div>
              </div>
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label"><@spring.message code="label.firmwareversion"/>*</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="firmwareversion" name="firmwareversion" placeholder="firmwareversion" <#if firmwareversion??>value=${firmwareversion}</#if>>
               </div>
              </div>
              <div id="msg" class="text-center" style="color:red;font-size:14px;">
              	<#if msg?exists&&msg=="4"><font color="green"><@spring.message code="label.4"/>${msgdeviceid}<@spring.message code="label.5"/></font><#elseif msg?exists><@spring.message code="label."+msg/></#if>
              </div>
              <div class="row text-center" style="color:red;font-size:14px;">
	              <div class="col-sm-6"><button type="button" id="submitid"  class="btn btn-default" style="width:25%;"><@spring.message code="label.submit"/></button></div>
	              <div class="col-sm-6"><button type="button" id="resetform" class="btn btn-default" style="width:25%;"><@spring.message code="label.reset"/></button></div>
              </div>
              
        </form>

        </div>

        <div class="col-md-1"></div>
    </div>
<!-- JavaScript 部分 -->
    <script type="text/javascript">
      $("#resetform").click(function(r){
      	$("input").val("");
      });
      $("#submitid").click(function (e) {
          document.getElementById("submitid").setAttribute("disabled", true);
          $("#typeform").submit();
      });
        $("#deviceid").change(function () {
            document.getElementById("submitid").removeAttribute("disabled");
        });
    </script>

<#include "../_foot0.ftl"/>