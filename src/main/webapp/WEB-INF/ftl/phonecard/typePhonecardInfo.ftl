<!-- 录入电话卡信息页面 -->
<#import "/spring.ftl" as spring />
<#include "../_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
          <form class="form-horizontal" id="typeform" action="add" method="POST">
          
              <div class="text-center"><h1><@spring.message code='label.enteringphonecardinfo'/></h1></div>
              <hr>
              <div  class="form-group">
                <label for="name"  class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code='label.serialnumber'/>*</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="serialnumber" name="serialnumber"  placeholder="<@spring.message code='label.serialnumber'/>" <#if serialnumber??>value=${serialnumber}</#if>>
                </div>
              </div>
              <div  class="form-group">
                <label for="code"  class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code='label.model'/></label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="model" name="model" placeholder="<@spring.message code='label.model'/>" <#if model??>value=${model}</#if> >
                </div>
              </div>
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label"  style="text-align: left;"><@spring.message code='label.firmwareversion'/></label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="firmwareversion" name="firmwareversion"  placeholder="<@spring.message code='label.firmwareversion'/>" <#if firmwareversion??>value=${firmwareversion}</#if>>
               </div>
              </div>
              <div  class="form-group">
                <label for="name"  class="col-sm-2 control-label"  style="text-align: left;">Rate Plan</label>
                <div class="col-sm-10">
                 <input type="text" class="form-control" id="rateplan" name="rateplan"  placeholder="Rate Plan" <#if rateplan??>value=${rateplan}</#if>>
                </div>
              </div>
              
              <div  class="form-group">
                <label for="code"  class="col-sm-2 control-label"  style="text-align: left;">Activation Date</label>
                <div class="col-sm-10"  align="left">
                 <#--<input type="text" class="form-control" id="activationdate" name="activationdate"  placeholder="Activation Date">-->
		                <div class="col-sm-5 input-group date form_datetime">
		                  <input class="form-control" size="16" name="activationdate" type="text" readonly placeholder="Activation Date" <#if activationdate?exists>value=${activationdate?string("yyyy-MM-dd")!}</#if>>
		                  <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						  <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
		                </div>	
                </div>
              </div>
              
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label"  style="text-align: left;">First programmed On</label>
                <div class="col-sm-10" align="left">
		                <div class="col-sm-5 input-group date form_datetime">
		                  <input class="form-control" size="16" name="firstprogrammedondate" type="text" readonly placeholder="First programmed On" <#if firstprogrammedondate?exists>value=${firstprogrammedondate?string("yyyy-MM-dd")!}</#if>>
		                  <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						  <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
		                </div>	
               		</div>
              </div>
              
              <div  class="form-group">
                <label for="name"  class="col-sm-2 control-label" style="text-align: left;">Last Programmed On</label>
                <div class="col-sm-10"  align="left">
		                <div class="col-sm-5 input-group date form_datetime">
		                  <input class="form-control" size="16" name="lastprogrammedondate" type="text" readonly placeholder="Last Programmed On" <#if lastprogrammedondate?exists>value=${lastprogrammedondate?string("yyyy-MM-dd")!}</#if>>
		                  <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						  <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
		                </div>	
                </div>
              </div>
              
              <div  class="form-group">
                <label for="code"  class="col-sm-2 control-label" style="text-align: left;">Last Saved On</label>
                <div class="col-sm-10"  align="left">
		                <div class="col-sm-5 input-group date form_datetime">
		                  <input class="form-control" size="16" name="lastsavedondate" type="text" readonly placeholder="Last Saved On" <#if lastsavedondate?exists>value=${lastsavedondate?string("yyyy-MM-dd")!}</#if>>
		                  <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						  <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
		                </div>	
                </div>
              </div>
              
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label" style="text-align: left;">Ordering Date</label>
                <div class="col-sm-10"  align="left">
		                <div class="col-sm-5 input-group date form_datetime">
		                  <input class="form-control" size="16" name="orderingdate" type="text" readonly placeholder="Ordering Date" <#if orderingdate?exists>value=${orderingdate?string("yyyy-MM-dd")!}</#if>>
		                  <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						  <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
		                </div>	
               </div>
              </div>
              
              <div  class="form-group">
                <label for="answer"  class="col-sm-2 control-label" style="text-align: left;">Expiredate Date</label>
                <div class="col-sm-10"  align="left">
		                <div class="col-sm-5 input-group date form_datetime">
		                  <input class="form-control" size="16" name="expiredate" type="text" readonly placeholder="Expiredate Date" <#if expiredate?exists>value=${expiredate?string("yyyy-MM-dd")!}</#if>>
		                  <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						  <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
		                </div>	
               </div>
              </div>
              
              <div id="msg" class="text-center" style="color:red;font-size:14px;">
              <#if msg?exists&&msg=="6"><font color="green"><@spring.message code="label.6"/> ${msgserialnumber} <@spring.message code="label.5"/></font><#elseif msg?exists><@spring.message code="label."+msg/></#if>
              </div>
              <div class="row text-center">
	              <div class="col-sm-6"><button id="submitid" type="button" class="btn btn-default" style="width:25%;"><@spring.message code='label.submit'/></button></div>
	              <div class="col-sm-6"><button type="button" id="resetform" class="btn btn-default" style="width:25%;"><@spring.message code='label.reset'/></button></div>
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
    $("#serialnumber").change(function () {
        document.getElementById("submitid").removeAttribute("disabled");
    });
	  $('.form_datetime').datetimepicker({
        //language:  'fr',
        format: 'yyyy-mm-dd',
        weekStart: 1,
        minView:'month',
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1
    });
    </script>
<#include "../_foot0.ftl"/>