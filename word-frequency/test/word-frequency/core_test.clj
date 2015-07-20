(ns word-frequency.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [word-frequency.core :as wc]))

(def my-fill)

(deftest stop-word-test-1
  (testing "Getting stop-words from file"
    (is (= (stop-word-set "/Users/shray/GitHub/Text-analysis/word-frequency/test/word-frequency/Stopwords.txt")
           #{"" "itself" "more" "didn't" "hers" "what's" "when's" "further" "we'll" "they'd" "his" "him" "hasn't" "how's" "are" "don't" "very" "you'd" "under" "who" "where's" "which" "we'd" "of" "this" "after" "once" "up" "off" "she" "shan't" "nor" "does" "here's" "theirs" "yours" "not" "mustn't" "it" "over" "ours\t" "he'd" "by" "there's" "she's" "it's" "is" "few" "shouldn't" "why" "doing" "about" "they" "you" "its" "than" "those" "where" "we're" "for" "should" "they'll" "cannot" "my" "again" "themselves" "ourselves" "whom" "yourselves" "he'll" "they're" "because" "can't" "any" "most" "you've" "you're" "were" "did" "was" "that" "if" "same" "both" "i'll" "i've" "doesn't" "had" "what" "an" "or" "she'll" "have" "couldn't" "am" "won't" "their" "a" "so" "them" "that's" "weren't" "on" "own" "above" "but" "when" "until" "be" "having" "out" "aren't" "herself" "and" "i'm" "do" "myself" "i" "down" "here" "too" "between" "such" "against" "she'd" "each" "how" "other" "from" "would" "these" "while" "no" "with" "some" "himself" "all" "you'll" "wouldn't" "then" "isn't" "could" "through" "yourself" "has" "haven't" "being" "our" "during" "who's" "before" "he's" "only" "your" "to" "into" "i'd" "why's" "we" "as" "we've" "ought" "wasn't" "he" "me" "at" "below" "the" "let's" "they've" "her" "been" "there" "in" "hadn't"}))))

(deftest stop-word-test-2
  (testing "Getting empty file of stop-words"
    (is (= (stop-word-set "/Users/shray/GitHub/Text-analysis/word-frequency/test/word-frequency/Empty.txt")
           #{""}))))

(deftest read-file-test-1
  (testing "Reading a file and putting each word into a vector"
    (is (= (read-file "/Users/shray/GitHub/Text-analysis/word-frequency/test/word-frequency/AliceInWonderlandExcerpt.txt")
           ["" "alice" "was" "beginning" "to" "get" "very" "tired" "of" "sitting" "by" "her" "sister" "on" "the" "bank," "and" "of" "having" "nothing" "to" "do:" "once" "or" "twice" "she" "had" "peeped" "into" "the" "book" "her" "sister" "was" "reading," "but" "it" "had" "no" "pictures" "or" "conversations" "in" "it," "'and" "what" "is" "the" "use" "of" "a" "book,'" "thought" "alice" "'without" "pictures" "or" "conversations?'" "" "so" "she" "was" "considering" "in" "her" "own" "mind" "(as" "well" "as" "she" "could," "for" "the" "hot" "day" "made" "her" "feel" "very" "sleepy" "and" "stupid)," "whether" "the" "pleasure" "of" "making" "a" "daisy-chain" "would" "be" "worth" "the" "trouble" "of" "getting" "up" "and" "picking" "the" "daisies," "when" "suddenly" "a" "white" "rabbit" "with" "pink" "eyes" "ran" "close" "by" "her." "" ""]))))

(deftest read-file-test-2
  (testing "Reading an empty file returns empty vector"
    (is (= (read-file "/Users/shray/GitHub/Text-analysis/word-frequency/test/word-frequency/Empty.txt")
           []))))

(deftest filter-stop-test-1
  (testing "Removes all stop-words from vector of words"
    (is (= (filter-stop  #{"" "itself" "more" "didn't" "hers" "what's" "when's" "further" "we'll" "they'd" "his" "him" "hasn't" "how's" "are" "don't" "very" "you'd" "under" "who" "where's" "which" "we'd" "of" "this" "after" "once" "up" "off" "she" "shan't" "nor" "does" "here's" "theirs" "yours" "not" "mustn't" "it" "over" "ours\t" "he'd" "by" "there's" "she's" "it's" "is" "few" "shouldn't" "why" "doing" "about" "they" "you" "its" "than" "those" "where" "we're" "for" "should" "they'll" "cannot" "my" "again" "themselves" "ourselves" "whom" "yourselves" "he'll" "they're" "because" "can't" "any" "most" "you've" "you're" "were" "did" "was" "that" "if" "same" "both" "i'll" "i've" "doesn't" "had" "what" "an" "or" "she'll" "have" "couldn't" "am" "won't" "their" "a" "so" "them" "that's" "weren't" "on" "own" "above" "but" "when" "until" "be" "having" "out" "aren't" "herself" "and" "i'm" "do" "myself" "i" "down" "here" "too" "between" "such" "against" "she'd" "each" "how" "other" "from" "would" "these" "while" "no" "with" "some" "himself" "all" "you'll" "wouldn't" "then" "isn't" "could" "through" "yourself" "has" "haven't" "being" "our" "during" "who's" "before" "he's" "only" "your" "to" "into" "i'd" "why's" "we" "as" "we've" "ought" "wasn't" "he" "me" "at" "below" "the" "let's" "they've" "her" "been" "there" "in" "hadn't"}
                         ["" "alice" "was" "beginning" "to" "get" "very" "tired" "of" "sitting" "by" "her" "sister" "on" "the" "bank," "and" "of" "having" "nothing" "to" "do:" "once" "or" "twice" "she" "had" "peeped" "into" "the" "book" "her" "sister" "was" "reading," "but" "it" "had" "no" "pictures" "or" "conversations" "in" "it," "'and" "what" "is" "the" "use" "of" "a" "book,'" "thought" "alice" "'without" "pictures" "or" "conversations?'" "" "so" "she" "was" "considering" "in" "her" "own" "mind" "(as" "well" "as" "she" "could," "for" "the" "hot" "day" "made" "her" "feel" "very" "sleepy" "and" "stupid)," "whether" "the" "pleasure" "of" "making" "a" "daisy-chain" "would" "be" "worth" "the" "trouble" "of" "getting" "up" "and" "picking" "the" "daisies," "when" "suddenly" "a" "white" "rabbit" "with" "pink" "eyes" "ran" "close" "by" "her." "" ""])
           ["alice" "beginning" "get" "tired" "sitting" "sister" "bank," "nothing" "do:" "twice" "peeped" "book" "sister" "reading," "pictures" "conversations" "it," "'and" "use" "book,'" "thought" "alice" "'without" "pictures" "conversations?'" "considering" "mind" "(as" "well" "could," "hot" "day" "made" "feel" "sleepy" "stupid)," "whether" "pleasure" "making" "daisy-chain" "worth" "trouble" "getting" "picking" "daisies," "suddenly" "white" "rabbit" "pink" "eyes" "ran" "close" "her."] ))))

(deftest filter-stop-test-2
  (testing "Removes only empty strings given a set of only an empty string"
    (is (= (filter-stop #{""}
                        ["" "alice" "was" "beginning" "to" "get" "very" "tired" "of" "sitting" "by" "her" "sister" "on" "the" "bank," "and" "of" "having" "nothing" "to" "do:" "once" "or" "twice" "she" "had" "peeped" "into" "the" "book" "her" "sister" "was" "reading," "but" "it" "had" "no" "pictures" "or" "conversations" "in" "it," "'and" "what" "is" "the" "use" "of" "a" "book,'" "thought" "alice" "'without" "pictures" "or" "conversations?'" "" "so" "she" "was" "considering" "in" "her" "own" "mind" "(as" "well" "as" "she" "could," "for" "the" "hot" "day" "made" "her" "feel" "very" "sleepy" "and" "stupid)," "whether" "the" "pleasure" "of" "making" "a" "daisy-chain" "would" "be" "worth" "the" "trouble" "of" "getting" "up" "and" "picking" "the" "daisies," "when" "suddenly" "a" "white" "rabbit" "with" "pink" "eyes" "ran" "close" "by" "her." "" ""])
           ["alice" "was" "beginning" "to" "get" "very" "tired" "of" "sitting" "by" "her" "sister" "on" "the" "bank," "and" "of" "having" "nothing" "to" "do:" "once" "or" "twice" "she" "had" "peeped" "into" "the" "book" "her" "sister" "was" "reading," "but" "it" "had" "no" "pictures" "or" "conversations" "in" "it," "'and" "what" "is" "the" "use" "of" "a" "book,'" "thought" "alice" "'without" "pictures" "or" "conversations?'" "so" "she" "was" "considering" "in" "her" "own" "mind" "(as" "well" "as" "she" "could," "for" "the" "hot" "day" "made" "her" "feel" "very" "sleepy" "and" "stupid)," "whether" "the" "pleasure" "of" "making" "a" "daisy-chain" "would" "be" "worth" "the" "trouble" "of" "getting" "up" "and" "picking" "the" "daisies," "when" "suddenly" "a" "white" "rabbit" "with" "pink" "eyes" "ran" "close" "by" "her."]))))

(deftest sort-freq-test
  (testing "Gets vector of elements and sorts them by the frequency of their occurence"
    (is (= (sort-freq ["alice" "beginning" "get" "tired" "sitting" "sister" "bank," "nothing" "do:" "twice" "peeped" "book" "sister" "reading," "pictures" "conversations" "it," "'and" "use" "book,'" "thought" "alice" "'without" "pictures" "conversations?'" "considering" "mind" "(as" "well" "could," "hot" "day" "made" "feel" "sleepy" "stupid)," "whether" "pleasure" "making" "daisy-chain" "worth" "trouble" "getting" "picking" "daisies," "suddenly" "white" "rabbit" "pink" "eyes" "ran" "close" "her."])
           '(["pictures" 2] ["sister" 2] ["alice" 2] ["do:" 1] ["book" 1] ["white" 1] ["it," 1] ["made" 1] ["sleepy" 1] ["twice" 1] ["suddenly" 1] ["worth" 1] ["tired" 1] ["picking" 1] ["sitting" 1] ["eyes" 1] ["hot" 1] ["bank," 1] ["'and" 1] ["pleasure" 1] ["trouble" 1] ["her." 1] ["whether" 1] ["stupid)," 1] ["pink" 1] ["nothing" 1] ["ran" 1] ["'without" 1] ["making" 1] ["book,'" 1] ["rabbit" 1] ["getting" 1] ["daisies," 1] ["close" 1] ["mind" 1] ["considering" 1] ["thought" 1] ["well" 1] ["conversations" 1] ["peeped" 1] ["daisy-chain" 1] ["beginning" 1] ["day" 1] ["feel" 1] ["reading," 1] ["use" 1] ["could," 1] ["get" 1] ["conversations?'" 1] ["(as" 1])))))

(deftest get-most-and-least-common-test
  (testing "Test that correct most and least common words are attained"
    (is (= (get-most-and-least-common  "/Users/shray/GitHub/Text-analysis/word-frequency/test/word-frequency/Stopwords.txt"
                                        "/Users/shray/GitHub/Text-analysis/word-frequency/test/word-frequency/AliceInWonderland.txt"
                                        5)
           {:most-common '("said" "alice" "'i" "little" "one"), 
            :least-common '("part.'" "prizes!'" "pencil" "kick," "caterpillar;")}))))


