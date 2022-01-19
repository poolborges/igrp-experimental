(function(){
	
	var com;

	var Colors = $.IGRP.components.colorPalettes.colors;

	var colorsIdx = -1;

	function getRandomColor(size) {
		
		var keys = Object.keys(Colors);

		if(size < keys.length){
			
			colorsIdx++;

			return  Colors[ keys[colorsIdx] ].color;

		}else{

			return ('#'+'0123456789abcdef'.split('').map(function(v,i,a){

			  		return i>5 ? null : a[Math.floor(Math.random()*16)] 

			  	}).join('')

			);

		}	

	};

	function _export(chart,type){

    	var name  = $(chart.container).parents('.gen-container-item').attr('item-name')+'.'+$.IGRP.getPageInfo(),

    		title = $(chart.container).parents('.gen-container-item').find('>.box-header>.box-title').text(),

    		opts  = $.extend(true,{},chart.options);

    	var data = {	
		    options: JSON.stringify(opts),
		    filename: title || name,
		    type: type,
		    async: true
		};

		var exportUrl = '//export.highcharts.com/';

		$.post(exportUrl, data, function(d) {
		    
		    var url = exportUrl + d,

		    	moz = navigator.userAgent.toLowerCase().indexOf('firefox') > -1;
		 
			if( $('html').hasClass('ie') || moz )
				
				window.open(url,'_blank');

			else{

				var file_path = url,

					a 		  = document.createElement('A'),

					extension = type ? type.split('/')[1] : null;

				extension = extension || 'pdf';

				a.href = file_path;
					
				a.download = (title || name)+'.'+extension ;

				a.target = '_newtab';
				
				document.body.appendChild(a);
				
				a.click();
				

			}

		});

    };

	$.IGRP.component('charts',{

		utils : {
			resizeChart : function(chart){
				try{ chart.reflow() }catch(e){ console.log(e)}
			},
			getAllCharts : function(parent){
				return parent && parent[0] ? $('[data-highcharts-chart]',parent) : $('[data-highcharts-chart]');
			},
			resizeAllCharts : function(parent){
				$.each(com.utils.getAllCharts(parent),function(){
		            com.utils.resizeChart( $(this).highcharts() );
		        });
			}
		},

		getTypeChart : function(type){
			var t = 'line';

			if (type == 'STACKEDAREA' || type == 'STACKEDPERCENTAREA')
				t = 'area';
			else if(type == 'STACKEDBAR')
				t = 'bar';
			else if(type == 'STACKEDCOLUMN' || type == 'STACKEDPERCENTCOLUMN' || type == 'STACKEDGROUPEDCOLUMN')
				t = 'column'
			else 
				t = type.toLowerCase();

			return t;
		},

		creatItem : function(e,imgpath){
			return '<a class="dropdown-item" chart-type="'+e.value+'">'+
				'<img src="'+imgpath+e.value+'.svg" class="icon"/><span>'+
				e.label+'</span></a>';
		},
		setFilter : function(o,type,filter){
			if (!o.hasClass('hasfilter')) {
				var imgpath = path+'/plugins/highcharts/img/',
					item    = '';
				
				$('.toggleChart .active-chart',o).html('<img src="'+imgpath+com.getTypeChart(type)+'.svg"/><span class="caret"/>');
				
				$.IGRP.defaults.highcharts.types.forEach(function(e,i){
					if(filter[0]){
						if ($.inArray(e.value,filter) !=-1)
							item += com.creatItem(e,imgpath);
					}else
						item += com.creatItem(e,imgpath);
				});

				$('.toggleChart .dropdown-menu',o).html(item);
				
				o.addClass('hasfilter');
			}
		},

		charts : {
			PIE : function(p){
				var data   = [],
					colors = [];

				p.categories.forEach(function(e,i){
					data.push({
						name:e,
						y   :p.data[0][i]
					});
					colors.push(p.colors[i]);
				});

				if (data[0]) {
					data[0].selected = true;
					data[0].sliced   = true;
				}

				var chart = {
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false,
			            type: 'pie',
			            marginTop: 25
			        },
			        title: {
			            text:p.title
			        },
			        tooltip: {
			            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                	enabled: true,
			                	format : '{point.percentage:.1f} % ({point.y})'
			                },
			                showInLegend: true
			            }
			        },
			        series: [{
			            name: p.labels[0],
			            colorByPoint: true,
			            data: data
			        }]
			    };

				return {structure:chart, colors:colors};
			},
			SEMIPIE : function(p){
				var data   = [],
					colors = [];

				p.categories.forEach(function(e,i){
					data.push([e,p.data[0][i]]);
					colors.push(p.colors[i]);
				});
				
				var chart = {
				    chart: {
				        plotBackgroundColor: null,
				        plotBorderWidth: 0,
				        plotShadow: false,
				        marginTop: 25
				    },
				    title: {
				        text: p.labels[0],
				        align: 'center',
				        verticalAlign: 'middle',
				        y: 40
				    },
				    tooltip: {
				        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
				    },
				    plotOptions: {
				        pie: {
				            dataLabels: {
				                enabled: true,
				                distance: -50,
				                style: {
				                    fontWeight: 'bold',
				                    color: 'white'
				                }
				            },
				            startAngle: -90,
				            endAngle: 90,
				            center: ['50%', '75%'],
				            dataLabels: {
			                	enabled: true,
			                	formatter: function(){
			                		return this.percentage.toFixed(1)+' % ('+this.y+')';
			                	}
			                },
			                showInLegend: true
				        }
				    },
				    series: [{
				        type: 'pie',
				        name: p.labels[0],
				        innerSize: '50%',
				        data: data
				    }]
				};

				return {structure:chart, colors:colors};
			},
			PYFU : function(p){//type['pyramid','funnel']
				var data   = [],
					colors = [],
					type   = p.type.toLowerCase();

				p.categories.forEach(function(e,i){
					data.push([e,p.data[0][i]]);
					colors.push(p.colors[i]);
				});

				var chart = {
				    chart: {
				        type: type,
				        marginTop: 50
				    },
				    title: {
				        text: p.title
				    },
				    tooltip: {
			            formatter: function(){
			              return '<span style="color:'+this.point.color+'">\u25CF</span> '+
			              	this.key+'<br/>'+this.series.name+': <b>'+this.percentage.toFixed(1)+' %</b> ('+this.y+')';
			            }
			        },
				    plotOptions:{
				    	series:{
				    		dataLabels:{
				    			enabled:true,
				    			formatter:function(){
		              				return '<b>'+this.percentage.toFixed(1)+' %</b> ('+this.y+')';
		            			},
		            			color:(Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
		            			softConnector:true
		            		}
		            	}
		          	},
				    legend: {
				    	layout:'horizontal',
				    	align:'center',
				    	verticalAlign:'bottom',
				    	borderWidth: 0
				    },
				    series: [{
				        name: p.labels[0],
				        data: data
				    }]
				};

				var more = chart.plotOptions.series;
				more.center = ['40%', '50%'];
				more.width = '80%';

				if (type == 'funnel') {
					more.neckWidth = '30%';
					more.neckHeight = '25%';
				}

				return {structure:chart, colors:colors};
			},
			HEATMAP : function(p){
				var data   = [],
					colors = [];

				p.data.forEach(function(e,i){
					p.categories.forEach(function(ex,ix){
						var arr = [];
						arr.push(ix);
						arr.push(i);
						arr.push(e[ix]);
						data.push(arr);
						colors.push([0.3 * (ix*2) ,p.colors[ix]]);
					});
				});

				var chart =  {
				    chart: {
				        type: 'heatmap',
				        marginTop: 40,
				        marginBottom: 80,
				        plotBorderWidth: 1
				    },
				    title: {
				        text: p.title
				    },

				    xAxis: {
				        categories: p.categories
				    },

				    yAxis: {
				        categories: p.labels,
				        title: null
				    },

				    colorAxis: {
				        min: 0,
				        minColor: '#FFFFFF',
        				maxColor: Highcharts.getOptions().colors[0]
				    },

				    legend: {
				        align: 'right',
				        layout: 'vertical',
				        margin: 0,
				        verticalAlign: 'top',
				        y: 25,
				        symbolHeight: 280,
				        enabled: false
				    },

				    tooltip: {
				        formatter: function () {
				            return '<b>' + this.series.yAxis.categories[this.point.y] + '</b><br><b>' +
				                this.series.xAxis.categories[this.point.x]+ '</b><br><b>' + this.point.value + '</b>';
				        }
				    },
				    plotOptions : {
			        },
				    series: [{
				        name: p.desclabel,
				        borderWidth: 1,
				        data: data,
				        dataLabels: {
				            enabled: true,
				            color: '#000000'
				        }
				    }]

				};

				return {structure:chart, colors:colors};
			},
			TREEMAP : function(p){
				var data = [];

				p.labels.forEach(function(e,i){
					data.push({
						id 	  : e,
						name  : e,
						color : p.colors[i]
					})
				});

				p.data.forEach(function(e,i){
					e.forEach(function(ex,ix){
						data.push({
							name   : p.categories[ix]+' - '+ex+' ',
							parent : p.labels[i],
							value  : ex
						});
					});
				});

				var chart = {
					chart:{
				        marginTop: 50,
					},
					plotOptions:{
					},
					tooltip:{
						pointFormatter: function(){
							return this.name.split('-')[0]+': <b>'+this.value+'</b>';
						}
					},
				    series: [{
				        type: "treemap",
				        layoutAlgorithm: 'stripes',
				        alternateStartingDirection: true,
				        levels: [{
				            level: 1,
				            layoutAlgorithm: 'sliceAndDice',
				            dataLabels: {
				                enabled: true,
				                align: 'left',
				                verticalAlign: 'top',
				                style: {
				                    fontSize: '15px',
				                    fontWeight: 'bold'
				                }
				            }
				        }],
				        data: data
				    }],
				    title: {
				        text: p.title
				    }
				};

				return {structure:chart, colors:p.colors};
			},
			OTHERS : function(p){// type['LINE','AREA','COLUMN','SPLINE','BAR','STACKEDBAR','STACKEDAREA','STACKEDPERCENTAREA','STACKEDCOLUMN','STACKEDGROUPEDCOLUMN']
				var data   = [],
					colors = [],
					type   = p.type;

				p.labels.forEach(function(e,i){
					data.push({
						name:e,
						data:p.data[i]
					});
					colors.push(p.colors[i]);
				});
				
				var chart = {
					chart:{
						type: com.getTypeChart(type),
						marginTop: 50
					},
				    title: {
				    	text: p.title
				    },
				    subtitle: {
				        text: null
				    },
				    xAxis :{
				    	categories:p.categories
				    },
				    yAxis: {
				        title: {
				            text: null
				        },
				        plotLines: [{value: 0,width: 1,color: '#808080'}]
				    },
				    tooltip: {
			        },
			        plotOptions : {
			        },
				    legend: {
			            //reversed: true
			        },
				    series: data
				};

				if (type == 'STACKEDAREA' || type == 'STACKEDPERCENTAREA') {
					var stacking = 'normal';

					if (type == 'STACKEDPERCENTAREA'){
						stacking = 'percent';
						chart.tooltip.pointFormat = '<span style="color:{series.color}">{series.name}</span>: <b>{point.percentage:.1f}%</b> ({point.y:,.0f} )<br/>';
					} 
						
					chart.xAxis.tickmarkPlacement = 'on';
					chart.xAxis.title = {
						enabled:false
					};

					chart.tooltip.split = true;
					chart.plotOptions.area = {
			            stacking: stacking,
			            lineColor: '#666666',
			            lineWidth: 1,
			            marker: {
			                lineWidth: 1,
			                lineColor: '#666666'
			            }
			        }
				}
				else if(type == 'STACKEDCOLUMN' || type == 'STACKEDPERCENTCOLUMN' || type == 'STACKEDGROUPEDCOLUMN'){
					var stacking = 'normal';
					chart.stackLabels = {
			            enabled: true,
			            style: {
			                fontWeight: 'bold',
			                color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
			            }
			        };

			        if (type == 'STACKEDPERCENTCOLUMN') {
			        	stacking = 'percent'
			        	chart.tooltip = {
			        		shared: true,
        					pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>'
			        	};
			        	chart.yAxis.min = 0;
			        }else{
			        	chart.tooltip = {
			        		headerFormat: '<b>{point.x}</b><br/>',
        					pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
			        	};
			        }

			       chart.plotOptions.column = {
			            stacking: stacking,
			            dataLabels: {
			                enabled: false,
			                color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
			            }
			        }
				}

				return {structure:chart, colors:colors};
			},
			TABLECHARTS : function(p){
				var table 		= $('<table/>').addClass('table table-striped').attr('id','t'+p.id),
					holder  	= $('#'+p.id).addClass('table_graph'),
					thead 		= '<thead><tr><th>'+p.desclabel+'</th>',
					tbody 		= '<tbody>',
					node 		= '',
					clickable 	= '';

				if (p.url){
					clickable 	= 'clickable';
					table.attr('url',p.url);
				}

				p.categories.forEach(function(e,i){
					thead += '<th class="nod_'+i+'">'+e+'</th>';
				});

				p.data.forEach(function(arr,pos){
					var labels = p.labels[pos];
					tbody += '<tr z="'+labels+'">';
					tbody += '<td><span class="chart-label">'+labels+'</span></td>';
					arr.forEach(function(e,i){
						tbody += '<td class="'+clickable+'" x="'+p.categories[i]+'" y="'+e+'">'+
						'<span>'+e+'</span></td>';
					});
					tbody += '</tr>';
				});

				tbody += '</body>';

				thead += '</tr></thead>';
				table.append(thead).append(tbody);
				holder.html('').append(table);

				$(table).on('click','.clickable',function(){
					$.IGRP.utils.openChartURL({
                    	pUrl: p.url,
                    	pX  : $(this).attr('x'),
                    	pY  : $(this).attr('y'),
                    	pZ  : $(this).parent('tr').attr('z')
                  	});
				});
			}
		},

		getChartData : function(arr){
			var data = [];
			arr.forEach(function(e,i){
				var d = [];
				e.split('!').forEach(function(c,x){
					d.push(c*1);
				});
				data.push(d);
			});
			return data;
		},

		getStructure : function(type){
			var t = 'OTHERS',
				arrOthers = ['LINE','AREA','COLUMN','SPLINE','BAR','STACKEDBAR','STACKEDAREA','STACKEDPERCENTAREA','STACKEDCOLUMN','STACKEDGROUPEDCOLUMN','STACKEDPERCENTCOLUMN'];

			if (type == 'FUNNEL' || type == 'PYRAMID')
				t = 'PYFU';
			else if ($.inArray(type,arrOthers) == - 1)
				t = type;

			return t;
		},

		colors : {

			setted : {},

			get : function(size){

				var arr    = [];
		
				for(var i = 0; i < size; i++){

					arr.push( com.colors.unique(size) );

				}

				com.colors.setted = {};

				colorsIdx = -1;

				return arr;
		
			},

			unique:function(size){

				var color = getRandomColor(size);
				
				if(!com.colors.setted[color]){

					com.colors.setted[color] = true;

					return color;

				}else{

					return com.colors.unique(size);

				}

			}

		},

		renderCharts : function(p){
			var o 	 		= $(p.chart),
				wrapper     = o.parents('.gen-container-item'),
				title 		= $('>.box-header>.box-title',wrapper).text() || null,
				type 		= o.attr('chart-type') ? o.attr('chart-type').toUpperCase() : 'LINE',
				data 		= o.attr('chart-data') ? o.attr('chart-data').split('|') : [],
				id 	 		= o.attr('chart-id') ? o.attr('chart-id') : '',
				url  		= o.attr('chart-url') ? o.attr('chart-url') : '',
				labels 		= o.attr('chart-labels') ? o.attr('chart-labels').split('|') : [],
				categories  = o.attr('chart-categories') ? o.attr('chart-categories').split('|') : [],
				filterType  = o.attr('filter-type') ? o.attr('filter-type').split(',') : [],
				colors  	= o.attr('chart-colors') ? o.attr('chart-colors').split('|') : [];
	
			if (data[0]) {

				if(p.type){
					type  = p.type.toUpperCase();
					o.attr('chart-type',p.type);
				}

				
				if (!colors[0]) {
					switch(type){
						case 'FUNNEL':
						case 'PIE':
						case 'PYRAMID':
						case 'SEMIPIE':

							colors = com.colors.get(categories.length);

						break;

						default:
							colors = com.colors.get(com.getChartData(data).length);

					};
				}

				$('#'+id).removeClass('table_graph');

				//console.log(com.getStructure(type))
				var chart = com.charts[com.getStructure(type)]({
					data 		: com.getChartData(data),
					categories  : categories ,
					colors 		: colors,
					id 			: id,
					url 		: url,
					desclabel 	: o.attr('chart-desc-label') ? o.attr('chart-desc-label') : '',
					labels 		: labels,
					type 		: type,
					title  		: title
				});

				if (type != 'TABLECHARTS') {
					if (chart.colors[0]){
						if (type == 'HEATMAP') {
							chart.structure.colorAxis = {
						        min: -5,
						        stops:chart.colors
					    	};
						}
						chart.structure.colors = chart.colors;
					} 

					chart.structure.plotOptions.series = {
			            cursor:'pointer',
			            point : {
				            events:{
				                click:function(e){

				                	var pointX = this.categories || this.name,
				                		pointY = this.y || this.value,
				                		pointZ = this.series.name;

				                	if (type == 'HEATMAP') {
				                		pointX = categories[this.x];
				                		pointY = name[this.y],
				                		pointZ = this.value;
				                	}

				                	if (type == 'TREEMAP') {
				                		pointX = $.trim(pointX.split('-')[0]);
				                		pointZ = this.parent || this.name;
				                	}

				                  	$.IGRP.utils.openChartURL({
				                    	pUrl: url,
				                    	pX  : pointX,
				                    	pY  : pointY,
				                    	pZ  : pointZ
				                  	});
				                }
				            }
			            }
			        };

			        if (type != 'TREEMAP')
			        	chart.structure.plotOptions.series.showInLegend = true;

			        if (type == 'STACKEDBAR')
						chart.structure.plotOptions.series.stacking = 'normal';
					

			        chart.structure.responsive = {
				        rules: [{
				            condition: {
				                maxWidth: 320
				            },
				            chartOptions: {
				                legend: {
				                    align: 'left',
				                    verticalAlign: 'bottom',
				                    layout: 'horizontal'
				                },
				                yAxis: {
				                    labels: {
				                        align: 'left',
				                        x: 0,
				                        y: -5
				                    },
				                    title: {
				                        text: title
				                    }
				                },
				                subtitle: {
				                    text: null
				                },
				                credits: {
				                    enabled: false
				                }
				            }
				        }]
				    };

				    chart.structure.exporting = {
				    	buttons:{
				    		contextButton:{
				    			menuItems:[
				    				{
				    					textKey:"downloadPNG",
				    					onclick : function(e){
				    				
				    						_export(this,'image/png')
				    					}
				    				},
				    				{
				    					textKey:"downloadJPEG",
				    					onclick : function(e){
				    				
				    						_export(this,'image/jpeg')
				    					}
				    				},
				    				{
				    					textKey:"downloadPDF",
				    					onclick : function(e){
				    				
				    						_export(this,'application/pdf')
				    					}
				    				},
				    				/*{
				    					textKey:"downloadSVG",
				    					onclick : function(e){
				    				
				    						_export(this,'image/svg')
				    					}
				    				}*/
				    			]
				    		}
				    	}				    	
				    };
				 	

				 	var renderChart = Highcharts.chart(id,chart.structure);

				 	
				}
				if ($('.toggleChart',o)[0])

					com.setFilter(o,type,filterType);

				return renderChart;
			}
		},

		init:function(){
			
			com = this;

			/*var max    = 0,

				colors = [];

			$('.IGRP-highcharts').each(function(i,o){
				
				var labels = $(o).attr('chart-labels').split(','),

					length = labels.length;

				if( length > max )

					max = length;
				
			});

			var keys = Object.keys( Colors.names );

			for(var i = 0; i < max; i++){

				colors[i] = Colors.names[keys[i]];

			} */

			$('.IGRP-highcharts').each(function(i,o){
				com.renderCharts({
					chart : o 
				});
			});

			$('body').on('click','.toggleChart .dropdown-item',function(){
				var type 		 = $(this).attr('chart-type') || 'LINE',
					holder 		 = $(this).parents('.IGRP-highcharts'),
					filterType 	 = holder.attr('filter-type') || '',
					contentChart = holder.parents('.col.graph:first'),
					itemName 	 = holder.attr('item-name'),
					remote       = holder.attr('remote-filter') ? holder.attr('remote-filter') : '';

				remote = remote == 'true' ? true : false;

				$('.active-chart img',holder).attr(
					'src',
					path+'/plugins/highcharts/img/'+com.getTypeChart(type)+'.svg'
				);

				if (remote) {

					$.IGRP.utils.createHidden({
						name:'p_fwl_charttype',
						value:type
					});

					$.ajax({

						url:$.IGRP.utils.getFormUrl($.IGRP.utils.getPageUrl()),

						success : function(data){
							var cheight 	= contentChart.height();

							contentChart.XMLTransform({
								xsl 			: path+'/xsl/tmpl/IGRP-charts.tmpl.xsl',
								xml 			: $(data).find('rows content '+itemName).getXMLDocument(),
								loading      	: true,
								xslParams 		: {
									pheight 		: cheight,
									filter  		: true,
									filter_type 	: filterType,
									remote_filter 	: remote
								},
								complete		: function(c){

									var obj    = $('.IGRP-highcharts',c),
										colors = $(data).find('rows content hidden[name="p_fwl_'+itemName+'_color"]').text();
									
									if (colors)
										obj.attr('chart-colors',colors);

									com.renderCharts({
										chart 		: obj,
										type  		: type,
										filter_type : filterType
									});
								},
								error		 	: function(c){
									console.log(c);
								}
							});
						},
						error : function(c){
							console.log(c);
						}
					});
				}else{

					com.renderCharts({
						chart 		: holder,
						type  		: type,
						filter_type : filterType
					});
				}
			});

			$.IGRP.on('sideBarToggle, windowResize',function(){
				com.utils.resizeAllCharts();
			});

			if($.IGRP.components.tabcontent)
				$.IGRP.components.tabcontent.on('tabActive',function(target){
					com.utils.resizeAllCharts( $(target) ); 
			});
		}

	},true);
})();


