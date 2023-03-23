import sys
from gpt_index import GPTSimpleVectorIndex

print('Query Is:', sys.argv[1])

# load from disk
index = GPTSimpleVectorIndex.load_from_disk('./storage/index.json')

print(index.query(sys.argv[1]))
