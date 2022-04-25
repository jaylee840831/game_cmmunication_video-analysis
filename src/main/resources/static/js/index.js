window.onload=function(){
}

$('body').on('click', '.game_btn', function() {
		let str=$(this).parent().parent().children().text();
		let code  = str.substring(0,6);
		console.log('球局代號 '+code);
		
		$.cookie('ball_game_code', code, { path: '/ballgame' });
		
		if(code!=null || code.length!=0){
			window.location='/ballgame';
		}
	});

function get_hello_message() {
	
	language='us'
	username=''
	welcome_message='/api/v1/GET/hello/'+language+'?username='+username
	
	$.ajax({
	    type: "GET",
	    url: welcome_message,
	    dataType: "text",
	    success: function (response) {
	    	console.log(response);
	    	$("#welcome_message").text(response)
	    },
	    error: function (thrownError) {
	      console.log(thrownError);
	    }
	  });
}

$(document).ready(function(){
	
	//取得球局資料
	gamedata='/api/v1/GET/gamedata/0'
	
	$('#data_table').html('');
	
	$('#data_table').append(
			'<thead>'
	            +'<tr>'
	            	+'<th>代號</th>'
	 				+'<th>發起人</th>'
	                +'<th>球局</th>'
	                +'<th>球場</th>'
	                +'<th>注意事項</th>'
	                +'<th>開始時間</th>'
	              	+ ' <th></th>'
           		+ '</tr>'
    		+'</thead>'
    		+'<tbody>');
	
	$.ajax({
	    type: "GET",
	    url: gamedata,
	    dataType: "json",
	    success: function (response) {
	    	/*console.log('球局資料')
	    	console.log(response);*/
	    	
	    	for(var i=0;i<response.length;i++){
	    		$('#data_table').append(
				            '<tr>'
				            	+'<td>'+response[i].code+'</td>'
				 				+'<td>'+response[i].username+'</td>'
				                +'<td>'+response[i].game+'</td>'
				                +'<td>'+response[i].court+'</td>'
				                +'<td>'+response[i].notice+'</td>'
				                +'<td>'+response[i].time+'</td>'
				              	+ "<td><input type='button' class='btn btn-dark game_btn' value='查看'></td>"
			           		+ '</tr>'
			           		);
	    	}
	    	
			$('#data_table').append('</tbody>'
					+'<tfoot>'
			            +'<tr>'
		            	+'<th>代號</th>'
		 				+'<th>發起人</th>'
		                +'<th>球局</th>'
		                +'<th>球場</th>'
		                +'<th>注意事項</th>'
		                +'<th>開始時間</th>'
		              	+ ' <th></th>'
	           			+ '</tr>'
    				+'</tfoot>');
			
			$('#data_table').DataTable();
	    },
	    error: function (thrownError) {
	      console.log(thrownError);
	    }
	  });
	
	$("#sidebarCollapse").on('click',function(){
		$("#sidebar").toggleClass('active');
	});
	
	$("#sidebarCollapse").animate({left: '-20px'}, "slow");
	
	language='tw'
	username=''
	//語言切換(英文)
	welcome_message='/api/v1/GET/hello/'+language+'?username='+username
	
	$.ajax({
	    type: "GET",
	    url: welcome_message,
	    dataType: "text",
	    success: function (response) {
	    	console.log(response);
	    	$("#welcome_message").text(response)
	    	if(response.includes("訪客") || response.includes("visiter")){
	    		$("#logout").text("登入");
	    	}
	    	else{
	    		$("#logout").text("登出");
	    	}
	    },
	    error: function (thrownError) {
	      console.log(thrownError);
	    }
	  });
	
	/*var data = $('#data_table > tbody > tr').map(function ()
			{
			    return $(this).children().map(function ()
			    {
			        return $(this);
			    });
			});
	
	for(var i=0;i<data.length;i++){
		for(var j=0;j<data[i].length;j++){
			console.log(data[i][j].text());
		}
	}*/
});

//var language=''
//var username=''
//var welcome_message=''

var vm = new Vue({
    el: '.container',
    data: {
        
    },
    methods: {
		
    },
});