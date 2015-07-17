(ns debug.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn in? 
  "true if seq contains elm"
  [seq elm]  
  (some #(= elm %) seq))

(defn my-filter-map
  [fun stop-words file-name list]
  (if (empty? list)
    '()
    (cons (fun (first list) stop-words file-name)
          (my-filter-map fun stop-words file-name (rest list)))))

(defn my-map
  [fun list]
  (if (empty? list)
    '()
    (cons (fun (first list)) (my-map fun (rest list)))))

(defn my-split-map-cat
  [list splitby]
  (if (empty? list)
    '()
    (concat (str/split (first list) splitby) (my-split-map-cat (rest list) splitby))))

(defn insert-word
  "inserts a given line into a given file"
  [word file last?]
  (if  (.exists (io/as-file file))
    (if last?
      (spit file (str word) :append true)
      (spit file (str word " ") :append true))
    (spit file word)
    ))

(defn create-stop-words 
  "creates a sequence of stop-words to make searching easier"
  [file]
  (str/split (slurp file) #"\n" ))

(defn filter-words
  "adds the line not in stop-words to file"
  [line stop-words file]
  (defn ins [word]
    (if (in? stop-words word)
      false
      (insert-word  word file false)
      ))
  (if (empty? line)
    (insert-word line file true)
    (my-map ins (butlast (str/split line #" ")))
    )
  (if (in? stop-words (last line))
    ((insert-word "" file true))
    (insert-word (last (str/split line #" ")) file true)
    ))

(defn get-max-5
  "gets the five most common words in the file"
  [file]
  (let [mapped (sort-by val > (frequencies (remove empty? (my-split-map-cat (str/split (slurp file) #"\n") #" " ))))]
    (defn rec [count mapp]
      (if (or (empty? mapp) (= count 0))
        '()
        (cons (ffirst mapp) (rec (dec count) (rest mapp)))))
    (rec 5 mapped)))

(defn get-min-5
  "gets the five most common words in the file"
  [file]
  (let [mapped (sort-by val < (frequencies (remove empty? (my-split-map-cat (str/split (slurp file) #"\n") #" " ))))]
    (defn rec [count mapp]
      (if (or (empty? mapp) (= count 0))
        '()
        (cons (ffirst mapp) (rec (dec count) (rest mapp)))))
    (rec 5 mapped)))




(defn get-10-words
  "gets the 5 most and 5 least common words in a file and puts them in a map"
  [file]
  {:top-5 (do (get-max-5 file))
   :bottom-5 (do (get-min-5 file))})

(defn text-analyze
  "main function... analyzes text and removes stop-words and returns five most and least common words"
  [to-read-file-name stop-words-file-name]
  (let [temp "temp-words.txt"]
    (my-filter-map filter-words
                   (create-stop-words stop-words-file-name)
                   temp
                   (str/split (slurp to-read-file-name) #"\r"))
    (if (.exists (io/as-file temp))
      (str  (get-10-words temp))
      (str "The file you gave me had no words that were not stop words. "))))

