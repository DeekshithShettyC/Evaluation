# Evaluation
Basic Security SetUp has been implemented(Role Based)
Backend Flow:
  User need to registe with username and password. API:/api/v1/public/register
  UserNeed to create profile using the api /api/v1/public/{username}/profile/Photographer/create
  update the profile using api /api/v1/public/{photographerId}/profile/update
  get the profile using /api/v1/publi/{username}/profile/get
  
  
==>user can uplaod video or Image with User Detail using api /api/v1/publi/{photographerId}/photo/add
    in Body we are passing 
      Category: ANIMAL or NATURE... for optimized search ablity
      MediaType:  VIDIO,IMAGE for image and vidio deferenciation.
      and other fields..
  
==>Get all Vide Or Image using api  /api/v1/publi/photosOrVideo/{mediaType}/getAll
  In Api URL passing MediaType:
         FOR FILTERING IMAGE and VIDEO
         
==>Get specific video or Image with User Detail  /api/v1/publi/{photographerId}/photos/{photoId}/{mediaType}/get
In Api URL passing MediaType:
         FOR FILTERING IMAGE and VIDEO
 
 ==>Add the Favorate item for theuser using /api/v1/public /{photographerId}/favourite/photos
 
 upload the image /api/v1/file/uploadFiles
  

  
