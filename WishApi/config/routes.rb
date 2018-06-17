Rails.application.routes.draw do
 namespace 'api' do
   namespace 'v1' do
     resources :user
     resources :signin
     resources :product
     resources :category
   end
 end
end
