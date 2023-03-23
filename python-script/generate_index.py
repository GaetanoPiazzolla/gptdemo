import glob
import sys
from pathlib import Path
from gpt_index import GPTSimpleVectorIndex, Document

def build_documents():
    documents = []
    for filename in glob.iglob(sys.argv[1] + '**/**/*.java', recursive=True):
        documents.append(open(filename, "r").read())

    for filename in glob.iglob(sys.argv[1] + '**/**/*.xml', recursive=True):
        documents.append(open(filename, "r").read())

    for filename in glob.iglob(sys.argv[1] + '**/**/*.properties', recursive=True):
        documents.append(open(filename, "r").read())
    return documents

print('directory to analyze:', sys.argv[1])

path = Path.cwd() / "storage/index.json"

documents = [Document(document) for document in build_documents()]
index = GPTSimpleVectorIndex(documents)
with open(path, 'w'):
    pass
index.save_to_disk(path)

