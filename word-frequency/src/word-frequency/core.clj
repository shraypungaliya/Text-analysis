(ns debug.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn stop-word-set
  [stopword-filename]
  (into #{}
        (conj (str/split
                 (slurp stopword-filename)
                 #"\n")
              "")))

(defn read-file
  [filename]
  (reduce concat 
          (for [x (with-open [rdr (clojure.java.io/reader filename)]
                     (reduce conj []
                             (line-seq rdr)))]
            (str/split (str/lower-case x)
                       #" "))))

(defn filter-stop
  [stop-set word-list]
  (remove stop-set
          word-list))

(defn sort-freq
  [stop-file word-file]
  (sort-by val
           >
           (frequencies (filter-stop (stop-word-set stop-file)
                                     (read-file word-file)))))

(defn get-upper-lower-five
  [stop-file word-file]
  (let [l (sort-freq stop-file word-file)]
    {:top-5  (map first
                  (take 5 l))
     :bottom-5 (map first
                    (take-last 5 l))}))
