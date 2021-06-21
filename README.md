# MoveToData_baseCocoDataset

1. 출발 디렉토리를 뒤져 파일이 나올때까지 루프를 돌린다.
2. 이미지 파일명과 같은 라벨명 존재하는지 체크
3. 중복 방지를 위해 밀리세컨 파일명 추가
4. 7대3 비율로 train val로 나눔
5. coco dataset 형식에 맞게 이미지와 라벨파일 복사해줌
6. train / val 각폴더의 파일명 읽어들여 txt 파일 만들어줌 
