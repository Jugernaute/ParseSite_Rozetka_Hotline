
/*
* choose page on pagination
* */
$('#pagination').on('click', function (e) {
    e.preventDefault();
    let fields = collectFields();

    $.ajax({
        url: '/main/page',
        type: 'post',
        data: JSON.stringify
        (
            {
                size : fields.num_items,
                search : fields.search,
                product : fields.enum_product,
                site : fields.site,
                page : fields.current_page
            }
        ),
        contentType: "application/json",
        success: function (result) {
            let count = fields.current_page*fields.num_items-fields.num_items;
            parseListFromController(result, count, fields.current_page);
        }
    })
});

/*
* how many items will be display in table
* */
$('.label-num-items').on('change',function () {
    let current_page = 1;
    let fields = collectFields();

    $.ajax({
        url: '/main/selectItems',
        type: 'post',
        data: JSON.stringify
        (
            {
                size : fields.num_items,
                search : fields.search,
                product : fields.enum_product,
                site : fields.site,
                page :current_page
            }
        ),
        contentType: "application/json",
        success: function (result) {
            console.log(result);
            let count = current_page*fields.num_items-fields.num_items;
            parseListFromController(result,count,current_page)
        }
    })
});

/*------------  search function   -------------------*/
/*
* execute after press key 'enter'
* */
function handle(e){
    if(e.keyCode === 13){
        let fields = collectFields();
        let current_page = 1;
        $.ajax({
            url: '/main/search',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify
            (
                {
                    search: fields.search,
                    size: fields.num_items,
                    site: fields.site,
                    page: current_page,
                    product: fields.enum_product
                }
            ),
            success: function (result) {
                let count = current_page*fields.num_items-fields.num_items;
                parseListFromController(result,count,current_page);

            }
        })

    }
    return false;
    }


$('.tbody tr').on('click','tr:has(.td_cl)', function(e) {
    e.preventDefault();
    let q = $(this).find('tr:hover a').text();
    $('.table').find('tr div.hidden').css("display","none");
    $('.table').find('tr:hover div.hidden').css("display","block");
    // $(this).css("display","block");
});


let on = $(document).on('click',function () {
    return $('body').find('.tbody .td_cl')
});

$(on).on('click',function () {
    $('.table').find('tr div.hidden').css("display","none");
    $('.table').find('tr:hover div.hidden').css("display","block");
});


$('.category_select').on('change', function () {
    selectCall()
});

$('.site-select').on('change', function () {
    selectCall();
});
$('a').attr($('td .giperlink a').val(), '_blank');
$('.tbody td a').on('click', function (e) {
    e.preventDefault();
    alert($(this).attr('href'));
});

                /*>>>>>>>>>>>>>>>>>>    functions   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
function selectCall() {
    if (
        $('.category_select option:selected').val() == "0" ||
        $('.site-select option:selected').val() == "0")
    {
        return;
    }
    $('.search').find('p').css('display', 'none');
    // let current_page = 1;
    let fields = collectFields();

    $.ajax({
        url: '/main/loadProducts',
        type: 'post',
        contentType: "application/json",
        data: JSON.stringify
        (
            {
                page: 1, // only 1 must be!!!
                search: fields.search,
                size: fields.num_items,
                product: fields.enum_product,
                site: fields.site
            }
        ),
        success: function (result) {
            let count = 1*fields.num_items-fields.num_items;
            parseListFromController(result,count, 1);
        }
    })
}
function parseListFromController(result, count, current_page) {
    $('.tbody').empty();
    let totalElements;
    $('.result-search').empty();
    $('.search').find('p').css('display','block');
    $.each(result, function (k, v) {
        let str = k.toString();
        let totalPage = str.substring(0, str.indexOf('.'));
        totalElements = str.substring(str.indexOf('.')+1, str.length);
        $(function() {
            $('#pagination').pagination({
                pages:totalPage,
                currentPage: current_page,
                cssStyle: 'light-theme'
            });
        });
        $.each(v, function (x, y) {
            count++;
            $('.tbody').append('<tr><th scope="row">'+count+'</th><td class="td_cl"><p style="color: blue">'+y.model+
                '</p><div class="hidden">'+y.descript+'</div></td>' +
                '<td>'+y.price+'</td><td><a href="'+y.linkOnSite+'" onclick="googleGo(\"'+y.linkOnSite+'\")" class="giperlink">link</a></td></tr>');
        })
    });
    $('.result-search').append(totalElements + " результатів");
}
function googleGo(link){
    window.open(link, '_blank')
}
/*
* collects information from:
* @search - input search;
* @enum_product - select of enumProduct;
* @current_page - from pagination selected page;
* @num_pages - from select how much items display in table;
* @site - select site;
* Its using for each request to DataBase, which return products
* */
function collectFields() {

        let responseArr = {};

    responseArr.current_page = $('.active .current').text();
    responseArr.search = $('.search input').val();
    responseArr.num_items = $('.num_items option:selected').val();
    responseArr.site = $('.site-select option:selected').text();
    responseArr.enum_product = $(".select .category_select:first-child option:selected").text();

        return responseArr;
}