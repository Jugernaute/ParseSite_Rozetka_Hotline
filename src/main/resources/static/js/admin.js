let $chooseSite = $('.choose-site');
let $chooseProd = $('.choose-product');
let $rozTelCount = $('tr:nth-child(3) td:nth-child(2) span:first-child');
let $rozTelDateUpdate = $('tr:nth-child(3) td:nth-child(2) span:last-child');
let $rozTabletCount = $('tr:nth-child(4) td:nth-child(2) span:first-child');
let $rozTabletDateUpdate = $('tr:nth-child(4) td:nth-child(2) span:last-child');
let $mobTabletCount = $('tr:nth-child(4) td:nth-child(3) span:first-child');
let $mobTabletDateUpdate = $('tr:nth-child(4) td:nth-child(3) span:last-child');
let $mobTelCount = $('tr:nth-child(3) td:nth-child(3) span:first-child');
let $mobTelDateUpdate = $('tr:nth-child(3) td:nth-child(3) span:last-child');




$('.btn-to-db').on('click', function () {
    let valSite = $chooseSite.find('select option:selected').val();
    let valProduct = $chooseProd.find('select option:selected').val();

    let $web = $('#web-set option:selected');
    let $wait = $('#wait-js option:selected');
    let $setCss = $('#set-css option:selected');
    let $setExept = $('#set-exeption option:selected');
    let $setJs = $('#set-js option:selected');

    if (valSite!= 0 && valProduct!=0){
        $('.error').addClass('hidden');

        let site =  $('.choose-site').find('select option:selected').text();
        let product = $('.choose-product').find('select option:selected').text();
        let textWeb = $web.text();
        let textWaitJs = $wait.text();
        let textCss = $setCss.text();
        let textJsEnabl = $setJs.text();
        let textExept = $setExept.text();

        $('#loading').removeClass('hidden');
        $('.main').addClass('blur');
        $.ajax({
			url: '/admin/uploadInDataBase',
            type: 'post',
            // contentType: "application/json",
			data: {site: site, product: product, waitForBackgroundJavaScript: textWaitJs, setCssEnabled: textCss,
                setJavaScriptEnabled: textJsEnabl, setThrowExceptionOnScriptError: textExept},
            // dataType: 'json',
			success: function (result) {
                // console.log(result);
                $('#loading').addClass('hidden');
                $('.main').removeClass('blur');
                $.each(result, function (k, v) {
                    switch (valSite) {
                        /*
                        * valProduct = 1 -> telephones
                        * valProduct = 2 -> tablets
                        *
                        * valSite = 1 -> rozetka
                        * valSite = 2 -> mobilluck
                        * */
                        case "1":
                            if (valProduct == "1"){
                                if (k==="listSize"){
                                    $rozTelCount.empty();
                                    $rozTelCount.html(v)
                                }else if (k === "dataUpdate") {
                                    $rozTelDateUpdate.empty();
                                    $rozTelDateUpdate.html(v);
                                }
                            } else if( valProduct == "2" ){
                                if (k==="listSize"){
                                    $rozTabletCount.empty();
                                    $rozTabletCount.html(v)
                                }else if (k === "dataUpdate") {
                                    $rozTabletDateUpdate.empty();
                                    $rozTabletDateUpdate.html(v);
                                }
                            }
                            break;
                        case "2":
                            if ( valProduct == "1" ){
                                if (k==="listSize"){
                                    $mobTelCount.empty();
                                    $mobTelCount.html(v)
                                }else if (k === "dataUpdate") {
                                    $mobTelDateUpdate.empty();
                                    $mobTelDateUpdate.html(v);
                                }
                            } else if( valProduct == "2" ){
                                // console.log(v);
                                // console.log(k);
                                if (k==="listSize"){
                                    $mobTabletCount.empty();
                                    console.log(v);
                                    $mobTabletCount.html(v)
                                }else if (k === "dataUpdate") {
                                    $mobTabletDateUpdate.empty();
                                    console.log(v);
                                    $mobTabletDateUpdate.html(v);
                                }
                            }
                            break;
                        default:
                            console.log("some error");
                    }
                })
            },
            error:function (error) {
                console.log(error);
                $('#loading').addClass('hidden');
                $('.main').removeClass('blur');
            }
		})
    }else if (valSite == 0 || valProduct == 0)
    $('.error').removeClass('hidden');
});

// $('.btn-to-db').on('click',function () {

// });
