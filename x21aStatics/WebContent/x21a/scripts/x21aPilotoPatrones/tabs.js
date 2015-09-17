jQuery(document).ready(function(){
	
	$("#tabs").rup_tabs({
		tabs : [
			{i18nCaption:"pestana0", tabs: [
				{i18nCaption:"sub00", tabs: [
					{i18nCaption:"sub000", url:"tab2Fragment"},
					{i18nCaption:"sub001", tabs: [
						{i18nCaption:"sub0010", url:"tab3Fragment"},
						{i18nCaption:"sub0011", tabs: [
							{i18nCaption:"sub00110", url:"tab2Fragment"},
							{i18nCaption:"sub00111", url:"fragmento1"}
						]}
					]},
					{i18nCaption:"sub002", url:"fragmento1"}
				]},
				{i18nCaption:"sub01", url:"fragmento1"},
				{i18nCaption:"sub02", url:"fragmento1"}
			]},
			{i18nCaption:"pestana1", tabs: [
				{i18nCaption:"sub10", url:"tab2Fragment"},
				{i18nCaption:"sub11", url:"tab3Fragment"}
			]},
			{i18nCaption:"pestana2", url:"tab3Fragment"}
			
		]
		, disabled : {tabs: 2, pestana0: 1, pestana1: 1, sub001: 1}
	});
});