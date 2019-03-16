let $chooseSite = $('.choose-site');
let $chooseProd = $('.choose-product');
let $rozTelCount = $('tr:nth-child(3) td:nth-child(2) span:first-child');
let $rozTelDateUpdate = $('tr:nth-child(3) td:nth-child(2) span:last-child');
let $rozTabletCount = $('tr:nth-child(4) td:nth-child(2) span:first-child');
let $rozTabletDateUpdate = $('tr:nth-child(4) td:nth-child(2) span:last-child');




$('.btn-to-db').on('click', function () {
    let valSite = $chooseSite.find('select option:selected').val();
    let valProduct = $chooseProd.find('select option:selected').val();

    let $web = $('#web-set option:selected');
    let $wait = $('#wait-js option:selected');
    let $setCss = $('#set-css option:selected');
    let $setExept = $('#set-exeption option:selected');
    let $setJs = $('#set-js option:selected');

    let valWeb = $web.val();
    let valWait = $wait.val();
    let valSetCss = $setCss.val();
    let valExept = $setExept.val();
    let valJs = $setJs.val();

    if (valSite!= 0 && valProduct!=0){
        $('.error').addClass('hidden');
        let nameSite = $('.choose-site').find('select option:selected').text();
        let nameProduct = $('.choose-product').find('select option:selected').text();

        let textWeb = $web.text();
        let textWaitJs = $wait.text();
        let textCss = $setCss.text();
        let textJsEnabl = $setJs.text();
        let textExept = $setExept.text();
        $('#loading').removeClass('hidden');
        $('.main').addClass('blur');
        $.ajax({
			url: '/admin/loadInDataBase',
			data: {site: nameSite, product: nameProduct, /*browserVersion: textWeb,*/ waitForBackgroundJavaScript: textWaitJs, setCssEnabled: textCss,
                setJavaScriptEnabled: textJsEnabl, setThrowExceptionOnScriptError: textExept},
			success: function (result) {
                console.log(result);
                $('#loading').addClass('hidden');
                $('.main').removeClass('blur');
                $.each(result, function (k, v) {
                    if (valSite == 1 && valProduct==1){
                        if (k==="listSize"){
                            $rozTelCount.empty();
                            $rozTelCount.html(v)
                        }else if (k === "dataUpdate") {
                            $rozTelDateUpdate.empty();
                            $rozTelDateUpdate.html(v);
                        }

                    } else if (valSite == 1 && valProduct==2){
                        if (k==="listSize"){
                            $rozTabletCount.empty();
                            $rozTabletCount.html(v)
                        }else if (k === "dataUpdate") {
                            $rozTabletDateUpdate.empty();
                            $rozTabletDateUpdate.html(v);
                        }
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
