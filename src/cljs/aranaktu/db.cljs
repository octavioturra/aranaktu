(ns aranaktu.db)

(def default-db
  {
    :name "re-frame"
    :speeches {}
    :speakers {}
    :themes {}
    :form-login {
             :signin false
             :username nil
             :password nil
             :confirm-password nil
           }
    :now 0
    })
