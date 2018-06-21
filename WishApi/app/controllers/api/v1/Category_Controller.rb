module Api
  module V1
    class CategoryController< ApplicationController
      def index
        #s=Product.select("products.PID,products.DSC,Products.price,Products.inStock,Pimages.image").joins("INNER Join Pimages on products.PID = Pimages.product_id")
        s=Productcat.select('CID,Name').where('ParentCID is not null')
        render json: s, status: :ok
      end

    end
      end
        end
