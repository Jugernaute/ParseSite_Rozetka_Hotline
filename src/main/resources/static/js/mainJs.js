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


/*choose page on pagination--------------------------------------------------*/

$('#pagination').on('click', function (e) {
    e.preventDefault();
    let current_page = $(this).find('.active .current').text();
    let search = $('.search input').val();
    let num_pages = $('.num_pages option:selected').val();
    $.ajax({
        url: '/main/pagination/',
        data: {currentPage: current_page,
            num: num_pages,
            search: search},
        success: function (result) {
            let count = current_page*num_pages-num_pages;
            parseListFromController(result, count, current_page);
        }
    })
});

$('.label-num-pages').on('change',function () {
    let current_page = 1;
    let search = $('.search input').val();
    let num_pages = $('.num_pages option:selected').val();
    let site = $('.site-select option:selected').text();
    let enum_product = $(".select .category_select:first-child option:selected").text();
    let current = $(".active .current").text();
    // ul [name = 'treeview']> option: li.selected
    console.log(search);
    console.log(num_pages);
    console.log(site);
    console.log(enum_product);
    console.log(current);
    $.ajax({
        url: '/main/pagination/selectItems',
        type: 'post',
        data: JSON.stringify({size : num_pages, search : search, product : enum_product, site : site, page : current}),
        contentType: "application/json",
        // dataType: "json",
        success: function (result) {
            let count = current_page*num_pages-num_pages;
            parseListFromController(result,count,current_page)
        }
    })
});

/*------------  search function   -------------------*/
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
    let select_category = $(".category_select option:selected").val();
    let enum_product = $(".select .category_select:first-child option:selected").text();
    console.log(select_category);
    console.log(enum_product);
    if (select_category == 1) {

    }
    else if(select_category==2){
                                    //    планшети
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
    }
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