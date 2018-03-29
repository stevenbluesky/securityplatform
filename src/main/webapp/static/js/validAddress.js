
function validAddressB(){
    var bdetailaddress = $('#bdetailaddress').val();
    var bpostal = $('#bpostal').val();
    var bcountry = $('#bcountry').val();
    var bprovince = $('#bprovince').val();
    var bcity = $('#bcity').val();

    if(bdetailaddress || bpostal || bcountry || bprovince || bcity){
        if(!bcountry || !bprovince || !bcity) {
            return false;
        }
    }
    return true;
}
function validAddressC() {
    var cspostal = $('#cspostal').val();
    var cscountry = $('#cscountry').val();
    var csprovince = $('#csprovince').val();
    var cscity = $('#cscity').val();
    if(cspostal || cscountry || csprovince || cscity) {
        if(!cscountry ||!csprovince || !cscity) {
            return false;
        }
    }
    return true;
}
