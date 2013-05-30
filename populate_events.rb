
def add(start_num, end_num)
  (start_num..end_num).each do |i|
    `curl --location 'http://localhost:8080/entry/new' -d 'title=title#{i}&url=url#{i}&content=#{i}&category=standalone'`
  end
end

add(ARGV[0], ARGV[1])

