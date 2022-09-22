import re

def solution(new_id):
    # upper to lower
    new_id = new_id.lower()
    # remove [~!@#$%^&*()=+[{]}:?,<>/]
    new_id = re.sub(r'[~!@#$%^&*\(\)=+\[\{\]\}:\?,<>\/]', '', new_id)
    # remove [.] at left and right side of string
    new_id = new_id.rstrip(".").lstrip(".")
    #
    new_id = re.sub('(([.])\\2{1,})', '.', new_id) 
    # if new_id length is 0 replace id by a
    if not len(new_id):
        new_id = 'a'
    # 
    if 16 <= len(new_id):
        new_id = new_id[:15]
        new_id = new_id.rstrip(".")
    #
    if len(new_id) <= 2:
        while(len(new_id) != 3):
            new_id += new_id[-1]
    
    return new_id