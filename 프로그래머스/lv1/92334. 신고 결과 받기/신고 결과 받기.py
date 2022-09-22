def solution(id_list, report, k):
    received_num = {}
    report_ids = {}
    
    for id in id_list:
        received_num[id] = 0
        report_ids[id] = []
        
    for info in report:
        info = info.split()
        report_ids[info[1]].append(info[0])
        
    for id in id_list:
        report_set = set(report_ids[id])
        if k <= len(report_set):
            for rep_id in report_set:
                received_num[rep_id] += 1
    
    answer = [0 for _ in range(len(id_list))]
    for rep_idx in range(len(id_list)):
        rep_id = id_list[rep_idx]
        answer[rep_idx] = received_num[rep_id]
    
    
    return answer