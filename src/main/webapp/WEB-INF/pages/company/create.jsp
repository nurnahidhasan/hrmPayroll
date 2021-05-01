
<div class="panel">
    <div class="panel-body">
        <h3 class="title-hero">
            Create Company
        </h3>
        <div class="example-box-wrapper">
           <form:form method="POST" action="${contextPath}/emp/save" modelAttribute="empForm" class="form">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Required Field</label>
                            <div class="col-sm-6">
                                <input type="text" placeholder="Required Field" required class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Min-length</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-minlength="6" placeholder="At least 6 characters" required class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Max-length</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-maxlength="6" placeholder="At most 6 characters" required class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Range length</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-range="[5,10]" placeholder="Between 5 and 10 characters" required class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">RegExp</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-pattern="#[A-Fa-f0-9]{6}" placeholder="Hexadecimal Color Code" required class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Email</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-type="email" placeholder="Email address" required class="form-control">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">URL</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-type="url" placeholder="URL address" required class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Digits</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-type="digits" placeholder="Digits only" required class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Alphanum</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-type="alphanum" placeholder="Alphanumeric only" required class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Password</label>
                            <div class="col-sm-6">
                                <input type="text" id="ps1" required class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Repeat Password</label>
                            <div class="col-sm-6">
                                <input type="text" data-parsley-equalto="#ps1" required class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Checkbox</label>
                            <div class="col-sm-6">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" required name="terms">
                                        Accept Terms &amp; Conditions
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="bg-default content-box text-center pad20A mrg25T">
                    <button class="btn btn-lg btn-primary">Save Employee</button>
                </div>
            </form>
        </div>
    </div>
</div>
                    