(ns word-frequency.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:import [clojure.java.io]))

(def my-stop-file
  "/Users/shray/GitHub/Text-analysis/word-frequency/test/word-frequency/Stopwords.txt")
(def my-input-file
  "/Users/shray/GitHub/Text-analysis/word-frequency/test/word-frequency/AliceInWonderland.txt")

(defn read-file
  [filename]
  (->> filename
       slurp
       (re-seq #"\w+")
       (remove str/blank?)
       (map str/lower-case)))


(defn stop-word-set
  [stopword-filename]
  (into #{} (read-file stopword-filename)))

(defn remove-stop
  [stop-set word-list]
  (remove stop-set
          word-list))

(defn sort-freq
  [list]
  (sort-by val
           >
           (frequencies list)))

(defn get-most-and-least-common
  [stop-file word-file amount]
  (let [stop-words (stop-word-set stop-file)
        m (->> word-file
              read-file
              (remove-stop stop-words)
              sort-freq)]
    {:most-common  (map first
                        (take amount m))
     :least-common (map first
                    (take-last amount m))}))
