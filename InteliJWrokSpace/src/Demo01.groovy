/**
 * Created with IntelliJ IDEA.
 * User: maple
 * Date: 13-9-15
 * Time: 上午1:45
 * To change this template use File | Settings | File Templates.
 */
class Demo01 {
    String title
    String author

    def myPrint(String prefix){
        "$prefix : $title : $author"
    }

    static main(args){
        println("hello world")
        def demo01 = new Demo01()
        demo01.setTitle("Groovy in action")
        demo01.setAuthor("Unknown author")
        println(demo01.getTitle())
        println(demo01.myPrint("TEST"))

        if (1 && !0){
            println("1 is true")
        }

        if (!null)
            println("null is false")

        def i = 1
        while (i <= 10){
            print("$i-")
            i++
        }
        println()
        for (j in 0..<10)
            print("$j=")

        println()
        def list = [1,2,3,4,5]
        for (j in list){
            print("${j}:")
        }

        list = 0..10
        println()
        println 1+1.6

        def a = "10"
        def b = 20
        println a * b
        if (!null)
            println "Not Null"

        def myFairStringy = 'The rain in Spain stays mainly in the plain!'
        // Words that end with 'ain': \b\w*ain\b
        def BOUNDS = /\b/
        def rhyme = /$BOUNDS\w*ain$BOUNDS/
        def found = ''
        myFairStringy.eachMatch(rhyme){
            match ->
                found += match + ' '
        }
        println(found)

        found = ''
        (myFairStringy =~ rhyme).each {match ->
            found += match + ' '
        }
        println(found)
        found = myFairStringy.replaceAll(rhyme){it -  'ain' + '___'}
        println(found)

        ('xyz' =~ /(.)(.)(.)/).each {all,x,y,z ->
            println("$all : $x, $y, $z")
        }

        assert '123 123' =~ /\d{3}$/
        assert !('123 123' ==~ /\d{3}$/)

        def beasts = ['bear', 'wolf', 'dog', 'tiger', 'regex']
        println(beasts.grep(~/..../))
    }
}