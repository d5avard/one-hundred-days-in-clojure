(ns fwpd.main
  (:require [clojure.string :as str]))

(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parseline
  [string]
  (str/split string #"\n"))

(defn parsestring
  [string]
  (str/split string #","))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map parsestring
       (parseline string)))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn run [opts]
    (let [vampiredb (slurp filename)]
    (println vampiredb)))
