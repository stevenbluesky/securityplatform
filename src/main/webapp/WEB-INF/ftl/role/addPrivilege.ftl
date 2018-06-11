<#ftl strip_whitespace=true>
<#import "/spring.ftl" as spring />

<#include "../_head0.ftl"/>
    <div class="row-horizontal">
        <#--<div class="col-md-1"></div>-->
        <div class="col-md-12">
            <form id="defaultForm" action="addPrivilege" method="POST">
                <div class="text-center"><h1><@spring.message code="label.AddPrivilege"/></h1></div>
                <hr>
                <div  class="form-group">
                    <label for="privilegecode"  class="col-sm-2 control-label"><@spring.message code="label.PrivilegeCode"/>*</label>
                    <div class="col-sm-10">
                        <input type="text"  class="form-control" id="privilegecode" name="privilegecode" placeholder="<@spring.message code="label.PrivilegeCode"/>">
                    </div>
                </div>
                <div  class="form-group">
                    <label for="code"  class="col-sm-2 control-label"><@spring.message code="label.PrivilegeLabel"/>*</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="privilegelabel" name="privilegelabel" placeholder="<@spring.message code="label.PrivilegeLabel"/>">
                    </div>
                </div>
                <div class="row text-center" style="font-size:14px;">
                <div class="col-sm-6"><button type="submit"  class="btn btn-default" style="width:25%;"><@spring.message code="label.submit"/></button></div>
                    <div class="col-sm-6"><button type="reset" class="btn btn-default" style="width:25%;"><@spring.message code="label.reset"/></button></div>
                </div>

            </form>

        </div>

        <#--<div class="col-md-1"></div>-->
    </div>
<!-- JavaScript 部分 -->
    <script type="text/javascript">
        $(document).ready(function () {
            $('#defaultForm').bootstrapValidator({
                //       live: 'disabled',
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    privilegecode: {
                        validators: {
                            notEmpty: {
                                message: 'The privilege code is required and cannot be empty'
                            }
                        }
                    },
                    privilegelabel: {
                        validators: {
                            notEmpty: {
                                message: 'The privilege label is required and cannot be empty'
                            }
                        }
                    }
                }
            });
        });
    </script>

<#include "../_foot0.ftl"/>