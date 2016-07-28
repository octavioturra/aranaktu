(ns aranaktu.fixtures)

(def base-data {
    :themes {
        1 {
            :title "Geral"
            :icon "house"
        }
    }
    :users {
        1 {
            :nickname "urso braco"
            :experience 5
            :area 1
        }
    }
    :areas {
        1 {
            :description "full-stack"
            :avatar "http://lorempixum.com/120/120"
        }
    }
    :event {
        1 {
            :participants {
                1 {
                    :user 1
                }
            }
            :speeches {
                1 {
                    :name "Introdução"
                    :hour 1469811600000
                    :speaker 1
                    :theme 1
                }
            }
            :speakers {
                1 {
                    :user 1
                    :name "Mateus"
                    :quote "Fale, jovem"
                    :bio "Desenvolvedor at GuiaBolso"
                    :profile-pic "http://lorempixum.com/120/120?1"
                }
            }
            :nps [
                {
                    :speech 1
                    :user 1
                    :participant 1
                }
            ]
        }
    }
})
