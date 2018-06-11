module Api
  module V1
    class UserController< ApplicationController
      def index
        users=Users.select("fName,lName")
        render json:{status: 'succes',msg:'hi',data: users}, status: :ok
     end
     def create
       uid =SecureRandom.random_number(1100000011)
       passrd =Digest::MD5.hexdigest(params[:passWord])
       i=Users.create(UID: uid,fName: params[:Name],Email: params[:Mail],Bdate: params[:Bdate],pass: passrd)
       render json:{status: 'succes',msg:'Check your Email'}, status: :ok
     end
   end
  end
end
