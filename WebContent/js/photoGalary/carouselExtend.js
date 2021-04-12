var HomeGalleryCarousel = new Class({
	
	Extends: Moo3DCarousel,
	
	_caption: null,
	
	initialize: function(parent, options, captionLocation)
	{
		this.parent(parent, options);
		
		this._caption = captionLocation;
	},
	
	changeCaption: function(currentGallery)
	{
		var cGallery = currentGallery;
		
		// Change the dot colour
		$$('#homeGalleryDots span').setStyle('color', '#cccccc');
		
		$('dot' + cGallery).setStyle('color', '#FA0320');
		
		// Link
		var tagA = this._elements[cGallery];
		
		// Gallery image
		var galleryImage = tagA.getElement('img');
		
		// Alt Text
		var altText = galleryImage.get('alt');
		
		$(this._caption).set('text', altText);
		
		$(this._caption).addEvents(
		{
			mouseover: function()
			{
				this.setStyles({
					color: '#082F68',
					cursor: 'pointer'
				});
			},			
			mouseout: function()
			{
				this.setStyles({
					color: '#000000',
					cursor: 'default'
				});
			},
			click: function()
			{
				location.href = tagA;
			}
		});
		
	}
						   
						   
});