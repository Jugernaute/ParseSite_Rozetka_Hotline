/*  click on button load from site*/

$('.load-products').on('click', function () {
    let num_pages = $('.num_pages option:selected').val();
    let current_page = $(this).find('.active .current').text();
    $.ajax({
        url: 'http://localhost:8080/main/loadTelephones',
        data: {num: num_pages},
        success: function (result) {
            let count = 0;
                parseListFromController(result, count, current_page)
        },
    })
});


/*
* choose page on pagination
* */
$('#pagination').on('click', function (e) {
    e.preventDefault();
    let fields = collectFields();

    $.ajax({
        url: '/main/pagination/page',
        type: 'post',
        data: JSON.stringify
        (
            {
                size : fields.num_page,
                search : fields.search,
                product : fields.enum_product,
                site : fields.site,
                page : fields.current_page
            }
        ),
        contentType: "application/json",
        success: function (result) {
            let count = fields.current_page*fields.num_page-fields.num_page;
            parseListFromController(result, count, fields.current_page);
        }
    })
});

$('.label-num-pages').on('change',function () {
    let current_page = 1;
    let fields = collectFields();

    $.ajax({
        url: '/main/pagination/selectItems',
        type: 'post',
        data: JSON.stringify
        (
            {
                size : fields.num_page,
                search : fields.search,
                product : fields.enum_product,
                site : fields.site,
                page : fields.current_page
            }
        ),
        contentType: "application/json",
        success: function (result) {
            let count = current_page*fields.num_page-fields.num_page;
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
        let search = $('.search input').val();
        let current_page = 1;
        let num_pages = $('.num_pages option:selected').val();
        $.ajax({
            url: '/main/search',
            data: {search: search, num: num_pages},
            success: function (result) {
                console.log(result);
                $('.tbody').empty();
                $('.result-search').empty();
                let totalElements;
                $('.search').find('p').css('display','block');
                let count = current_page*num_pages-num_pages;
               $.each(result, function (k, v) {
                   let s = k.toString();
                   let number = s.indexOf('.');
                   let totalPage = s.substring(0, s.indexOf('.'));
                   totalElements = s.substring(s.indexOf('.')+1, s.length);
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
                           '<td>'+y.price+'</td><td><a href="'+y.linkOnSite+'" class="giperlink">link</a></td></tr>');
                   })
               });
                // parseListFromController(result,count,current_page);

                $('.result-search').append(totalElements + " результатів");
            }
        })

    }
    return false;
    }


$('.load-DB').on('click', function () {
    let current_page = $('#pagination').find('.active .current').text();
    $.ajax({
        url: 'http://localhost:8080/main/loadProductsDB',
        success: function (result) {
            $('.tbody').empty();
            let count = 0;
            $.each(result, function (k, v) {
                count++;
                $('.tbody').append('<tr><th scope="row">'+count+'</th><td class="td_cl"><p style="color: blue">'+v.model+
                    '</p><div class="hidden">'+v.descript+'</div></td>' +
                    '<td>'+v.price+'</td><td><a href="'+v.linkOnSite+'" class="giperlink">link</a></td></tr>')
            });
        }
    })
});


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

    $('.search').find('p').css('display', 'none');
    let enum_product = $(".select .category_select:first-child option:selected").text();
    console.log(enum_product);

        let current_page = 1;
        // console.log(current_page);
        let search = $('.search input').val();
        let num_pages = $('.num_pages option:selected').val();
        $.ajax({
            url: '/main/loadTablets',
            data: {/*currentPage: current_page,*/ search: search, num: num_pages, product: enum_product},
            success: function (result) {
                let count = current_page*num_pages-num_pages;
                // console.log(result);
                parseListFromController(result,count, current_page);
            }
    })
});

                /*>>>>>>>>>>>>>>>>>>    functions   <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

function parseListFromController(result, count, current_page) {
    $('.tbody').empty();
    $.each(result, function (k, v) {
        $(function() {
            $('#pagination').pagination({
                pages:k,
                currentPage: current_page,
                cssStyle: 'light-theme'
            });
        });
        $.each(v, function (x, y) {
            count++;
            $('.tbody').append('<tr><th scope="row">'+count+'</th><td class="td_cl"><p style="color: blue">'+y.model+
                '</p><div class="hidden">'+y.descript+'</div></td>' +
                '<td>'+y.price+'</td><td><a href="'+y.linkOnSite+'" class="giperlink">link</a></td></tr>');
        })
    })
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
    responseArr.num_page = $('.num_pages option:selected').val();
    responseArr.site = $('.site-select option:selected').text();
    responseArr.enum_product = $(".select .category_select:first-child option:selected").text();

        return responseArr;
}